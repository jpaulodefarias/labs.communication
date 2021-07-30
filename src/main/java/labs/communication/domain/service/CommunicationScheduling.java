package labs.communication.domain.service;

import labs.communication.domain.entity.Communication;
import labs.communication.domain.exception.InvalidCommunicationSchedulingException;
import labs.communication.domain.state.CommunicationStatus;
import labs.communication.infrastructure.repository.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@RequiredArgsConstructor
public class CommunicationScheduling {

    private final CommunicationRepository communicationRepository;

    private final Validator validator;

    @Transactional
    public Communication execute(Communication communication) {
        communication.setStatus(CommunicationStatus.SCHEDULED);
        validate(communication);
        return communicationRepository.save(communication);
    }

    private void validate(Communication communication) {
        var violations = validator.validate(communication);

        if (!violations.isEmpty()) {
            throw new InvalidCommunicationSchedulingException(violations);
        }
    }

}
