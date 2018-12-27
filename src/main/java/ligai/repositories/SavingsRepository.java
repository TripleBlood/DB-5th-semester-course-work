package ligai.repositories;

import ligai.models.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
    Savings getById(Long id);
}
