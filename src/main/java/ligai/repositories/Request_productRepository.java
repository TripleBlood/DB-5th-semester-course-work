package ligai.repositories;

import ligai.models.Product;
import ligai.models.Request;
import ligai.models.Request_product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

public interface Request_productRepository extends JpaRepository<Request_product, Long>{
    ArrayList<Request_product> findByRequest(Request request);

    Optional<Request_product> findFirstByRequestAndProduct(Request request, Product product);

    @Transactional
    Long deleteRequest_productByProduct(Product product);
}
