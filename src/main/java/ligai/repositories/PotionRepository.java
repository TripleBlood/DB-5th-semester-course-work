package ligai.repositories;

import ligai.models.Potion;
import ligai.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PotionsRepository extends JpaRepository<Potion,Long> {
    Optional<Product> findById(Long id);
}
