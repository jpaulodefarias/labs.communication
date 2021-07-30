package labs.communication.domain.service;

import labs.communication.domain.entity.Communication;
import labs.communication.infrastructure.repository.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunicationQuery {

    private final CommunicationRepository communicationRepository;

    public Optional<Communication> execute(Long id) {
        return communicationRepository.findById(id);
    }

}
