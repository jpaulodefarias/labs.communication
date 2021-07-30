package labs.communication.domain.service;

import labs.communication.domain.entity.Communication;
import labs.communication.infrastructure.repository.CommunicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommunicationSchedulingTest {

    @InjectMocks
    private CommunicationScheduling communicationScheduling;

    @Mock
    private CommunicationRepository communicationRepository;

    @Mock
    private Validator validator;

    @Test
    void execute() {
        var communication = new Communication();

        communicationScheduling.execute(communication);

        verify(validator).validate(any(Communication.class));
        verify(communicationRepository).save(any(Communication.class));
    }

}