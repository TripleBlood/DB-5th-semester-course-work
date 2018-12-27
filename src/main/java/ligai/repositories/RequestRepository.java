package ligai.repositories;

import ligai.enums.Request_status;
import ligai.models.Request;
import ligai.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findFirstByRequestStatusAndUser(Request_status request_status, User user);

    Optional<ArrayList<Request>> findAllByRequestStatus(Request_status request_status);

    Optional<Request> findFirstById(Long id);
    //@Modifying
    //@Query("update Request r set r.requestStatus = :status where r.id = :id")
    //int setValue(@Param("id") Long id, @Param("value") String status);
}

