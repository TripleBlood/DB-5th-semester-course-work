package ligai.repositories;

import ligai.models.Ingredient;
import ligai.models.Potion;
import ligai.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface PotionRepository extends JpaRepository<Potion,Long> {
    Optional<Potion> findById(Long id);
    Optional<Potion> findByName(String name);
}
