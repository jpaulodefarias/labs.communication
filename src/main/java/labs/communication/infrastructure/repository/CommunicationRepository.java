package labs.communication.infrastructure.repository;

import labs.communication.domain.entity.Communication;
import org.springframework.data.repository.CrudRepository;

public interface CommunicationRepository extends CrudRepository<Communication, Long> {
}
