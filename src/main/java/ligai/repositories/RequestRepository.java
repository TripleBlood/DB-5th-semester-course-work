package ligai.repositories;

import ligai.enums.Request_status;
import ligai.models.Request;
import ligai.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findFirstByRequestStatusAndUser(Request_status request_status, User user);
}

