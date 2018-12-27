package ligai.controllers;

import ligai.enums.Request_status;
import ligai.forms.NewProdInRequestForm;
import ligai.models.*;
import ligai.repositories.ProductRepository;
import ligai.repositories.RequestRepository;
import ligai.repositories.Request_productRepository;
import ligai.repositories.SavingsRepository;
import ligai.services.AuthenticationService;
import ligai.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ShopController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private Request_productRepository request_productRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private SavingsRepository savingsRepository;

    @GetMapping("/user/shop")
    public String retShopPage(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("products", productRepository.findAll());
        return "shop_basic";
    }

    @GetMapping("/user/shop/{product-id}")
    public String getAuto(@PathVariable("product-id") Long product_id, @ModelAttribute("model") ModelMap model) {
        if (productRepository.findById(product_id).isPresent()) {
            Product product = productRepository.findFirstById(product_id).get();
            model.addAttribute("product", product);
            return "shop_item";
        } else return "redirect:/user/shop";
    }

    @PostMapping("/user/shop/{product-id}")
    public String makeNewPotion(@PathVariable("product-id") Long product_id, @Valid @ModelAttribute("newProdInRequestForm") NewProdInRequestForm newProdInRequestForm,
                                BindingResult errors, RedirectAttributes attributes, Authentication authentication) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/user/shop/{product-id}";
        }
        if (requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, authenticationService.getUserByAuthentication(authentication)).isPresent()) {
            Product product = productRepository.findFirstById(product_id).get();
            requestService.addNewProductToRequest(newProdInRequestForm, product, requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, authenticationService.getUserByAuthentication(authentication)).get());
        } else {
            Product product = productRepository.findFirstById(product_id).get();
            requestService.createNewRequest(newProdInRequestForm, product, authenticationService.getUserByAuthentication(authentication));
        }

        return "redirect:/user/shop";
    }

    @GetMapping("/user/shop/cart")
    public String getCartPAge(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        User currentUser = authenticationService.getUserByAuthentication(authentication);
        if (!requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, currentUser).isPresent()) {
            requestService.createNewRequestNoItems(currentUser);
        }
        Request request = requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, currentUser).get();

        ArrayList<Request_product> request_products = request_productRepository.findByRequest(request);
        int sum = 0;
        for (Request_product req : request_products) {
            sum += req.getCount() * req.getProduct().getCost();
        }
        model.addAttribute("cartItems", request_products);

        model.addAttribute("sum", sum);

        return "shop_cart";
    }

    @GetMapping("/user/shop/cart/confirm")
    public String requestConfirm(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        User currentUser = authenticationService.getUserByAuthentication(authentication);
        if (!requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, currentUser).isPresent()) {
            requestService.createNewRequestNoItems(currentUser);
        }
        Request request = requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, currentUser).get();

        ArrayList<Request_product> request_products = request_productRepository.findByRequest(request);

        if (request_products.size() == 0) return "redirect:/user/shop";
        else {
            request.setRequestStatus(Request_status.COMIRMED);
            requestRepository.save(request);
            return "redirect:/user/shop/cart";
        }
    }

    @GetMapping("/user/shop/cart/abort/{product-id}")
    public String deleteItemFromRequest(@PathVariable("product-id") Long product_id, @ModelAttribute("model") ModelMap model, Authentication authentication) {
        User currentUser = authenticationService.getUserByAuthentication(authentication);

        if (productRepository.findById(product_id).isPresent()) {
            Product product = productRepository.findFirstById(product_id).get();

            if (!requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, currentUser).isPresent()) {
                requestService.createNewRequestNoItems(currentUser);
            }
            Request request = requestRepository.findFirstByRequestStatusAndUser(Request_status.NOT_CONFIRED, currentUser).get();

            request_productRepository.delete(request_productRepository.findFirstByRequestAndProduct(request, product).get());
            request_productRepository.flush();
            return "redirect:/user/shop/cart";
        } else return "redirect:/user/shop";
    }

    @GetMapping("/admin/requests")
    public String adminReqPage(@ModelAttribute("model") ModelMap model) {
        ArrayList<Request> requests = new ArrayList<>();
        if (requestRepository.findAllByRequestStatus(Request_status.NOT_CONFIRED).isPresent()) {
            requests = requestRepository.findAllByRequestStatus(Request_status.NOT_CONFIRED).get();
        }
        model.addAttribute("requests", requests);
        return "admin_req";
    }

    @GetMapping("/admin/requests/{request_id}")
    public String deleteItemFromRequest(@PathVariable("request_id") Long request_id, @ModelAttribute("model") ModelMap model) {

        Request request = requestRepository.findFirstById(request_id).get();

        ArrayList<Request_product> request_products = request_productRepository.findByRequest(request);

        int sum = 0;
        for (Request_product req : request_products) {
            sum += req.getCount() * req.getProduct().getCost();
        }

        model.addAttribute("reqItems", request_products);
        model.addAttribute("reqIdentifier", request.getId());

        model.addAttribute("sum", sum);

        return "admin_request_confirm";
    }

    @GetMapping("/admin/requests/confirm/{request_id}")
    public String makeRequestDone(@PathVariable("request_id") Long request_id, @ModelAttribute("model") ModelMap model) {

        Request request = requestRepository.findFirstById(request_id).get();

        ArrayList<Request_product> request_products = request_productRepository.findByRequest(request);

        for (Request_product req : request_products) {
            if (req.getCount() > req.getProduct().getAvailable()) return "no_error";
        }

        int sum = 0;
        for (Request_product req : request_products) {
            Product prod = req.getProduct();
            prod.setAvailable(req.getProduct().getAvailable() - req.getCount());
            productRepository.save(prod);
            sum += req.getCount() * req.getProduct().getCost();
        }
        long i = 1;
        Savings save = savingsRepository.getById(i);
        save.setAvailableMoney(save.getAvailableMoney() + sum);
        savingsRepository.save(save);

        request.setRequestStatus(Request_status.DONE);
        request.setCompletionDate(new Date());
        requestRepository.save(request);

        return "redirect:/admin/requests";
    }

}
