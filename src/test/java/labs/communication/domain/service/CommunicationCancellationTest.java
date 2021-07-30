package labs.communication.domain.service;

import labs.communication.domain.entity.Communication;
import labs.communication.domain.exception.InvalidCommunicationCancellationException;
import labs.communication.domain.state.CommunicationStatus;
import labs.communication.infrastructure.repository.CommunicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommunicationCancellationTest {

    @InjectMocks
    private CommunicationCancellation communicationCancellation;

    @Mock
    private CommunicationRepository communicationRepository;

    @Test
    void executeSuccess() {
        var communication = new Communication();
        communication.setStatus(CommunicationStatus.SCHEDULED);

        when(communicationRepository.findById(anyLong())).thenReturn(Optional.of(communication));

        communicationCancellation.execute(anyLong());

        verify(communicationRepository).save(any(Communication.class));
        assertThat(communication.getStatus()).isEqualTo(CommunicationStatus.CANCELED);
    }

    @Test
    void executeFail() {
        var communication = new Communication();

        when(communicationRepository.findById(anyLong())).thenReturn(Optional.of(communication));

        assertThrows(InvalidCommunicationCancellationException.class,
                () -> communicationCancellation.execute(anyLong()));
    }

}