package labs.communication.domain.service;

import labs.communication.infrastructure.repository.CommunicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommunicationQueryTest {

    @InjectMocks
    private CommunicationQuery communicationQuery;

    @Mock
    private CommunicationRepository communicationRepository;

    @Test
    void execute() {
        communicationQuery.execute(anyLong());

        verify(communicationRepository).findById(anyLong());
    }
}