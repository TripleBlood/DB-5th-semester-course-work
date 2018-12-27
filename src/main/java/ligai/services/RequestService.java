package ligai.services;

import ligai.forms.NewProdInRequestForm;
import ligai.models.Product;
import ligai.models.Request;
import ligai.models.User;

public interface RequestService {
    void addNewProductToRequest(NewProdInRequestForm newProdInRequestForm, Product product, Request request);
    void createNewRequest(NewProdInRequestForm newProdInRequestForm, Product product, User user);
    void createNewRequestNoItems(User user);
}
