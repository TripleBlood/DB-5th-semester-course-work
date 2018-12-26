package ligai.controllers;

import ligai.models.Product;
import ligai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class ShopConroller {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/user/shop")
    public String retShopPage(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("products", productRepository.findAll());
        return "shop_basic";
    }

    @GetMapping("/user/shop/{product-id}")
    public String getAuto(@PathVariable("product-id") Long product_id, @ModelAttribute("model") ModelMap model) {
        if (productRepository.findById(product_id).isPresent()){
            Product product =  productRepository.findFirstById(product_id).get();
            model.addAttribute("product", product);
            return "shop_item";
        }else return "redirect:/user/shop";
    }
}
