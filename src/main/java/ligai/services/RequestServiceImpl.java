package ligai.services;

import ligai.enums.Request_status;
import ligai.forms.NewProdInRequestForm;
import ligai.models.Product;
import ligai.models.Request;
import ligai.models.Request_product;
import ligai.models.User;
import ligai.repositories.ProductRepository;
import ligai.repositories.RequestRepository;
import ligai.repositories.Request_productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    Request_productRepository request_productRepository;

    @Override
    public void addNewProductToRequest(NewProdInRequestForm newProdInRequestForm, Product product, Request request) {
        Request_product requestProduct = Request_product.builder()
                .product(product)
                .request(request)
                .count(newProdInRequestForm.getQuantity())
                .build();

        request_productRepository.save(requestProduct);
    }

    @Override
    public void createNewRequest(NewProdInRequestForm newProdInRequestForm, Product product, User user) {
        Request request = Request.builder()
                .requestStatus(Request_status.NOT_CONFIRED)
                .creationDate(new Date())
                .user(user)
                .build();

        requestRepository.save(request);
        addNewProductToRequest(newProdInRequestForm, product, request);
    }

    @Override
    public void createNewRequestNoItems(User user){
        Request request = Request.builder()
                .requestStatus(Request_status.NOT_CONFIRED)
                .creationDate(new Date())
                .user(user)
                .build();

        requestRepository.save(request);
    }
}
