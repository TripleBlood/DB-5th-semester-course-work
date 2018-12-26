package ligai.repositories;

import ligai.models.Request;
import ligai.models.Request_product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Request_productRepository extends JpaRepository<Request_product, Long>{
    Optional<Request_product>  findByRequest(Request request);

}
