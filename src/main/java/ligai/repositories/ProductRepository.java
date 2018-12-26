package ligai.repositories;

import ligai.enums.Type;
import ligai.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.method.P;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    ArrayList<Product> findAllByType(Type type);

    Optional<Product> findByName(String name);

    Optional<Product> findById(Long id);

    Optional<Product> findFirstById(Long id);
}
