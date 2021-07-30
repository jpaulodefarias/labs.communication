package labs.communication.domain.service;

import labs.communication.domain.entity.Communication;
import labs.communication.domain.exception.CommunicationNotFoundException;
import labs.communication.domain.exception.InvalidCommunicationCancellationException;
import labs.communication.domain.state.CommunicationStatus;
import labs.communication.infrastructure.repository.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunicationCancellation {

    private final CommunicationRepository communicationRepository;

    @Transactional
    public void execute(Long id) {
        communicationRepository.findById(id)
                .ifPresentOrElse(communication -> {
                    validate(communication);
                    communication.setStatus(CommunicationStatus.CANCELED);
                    communicationRepository.save(communication);
                }, () -> {
                    throw new CommunicationNotFoundException();
                });
    }

    private void validate(Communication communication) {
        if (!CommunicationStatus.SCHEDULED.equals(communication.getStatus()))
            throw new InvalidCommunicationCancellationException();
    }

}
