package labs.communication.api.controller;

import labs.communication.api.resource.CommunicationInput;
import labs.communication.domain.entity.Communication;
import labs.communication.domain.service.CommunicationScheduling;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommunicationControllerTest {

    @InjectMocks
    private CommunicationController communicationController;

    @Mock
    private CommunicationScheduling communicationScheduling;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void schedule() {
        var input = new CommunicationInput();
        var mappedInput = new Communication();

        when(modelMapper.map(any(CommunicationInput.class), eq(Communication.class)))
                .thenReturn(mappedInput);

        var response = communicationController.schedule(input);

        verify(modelMapper).map(any(CommunicationInput.class), eq(Communication.class));
        verify(communicationScheduling).execute(any(Communication.class));

        assertThat(response).isNotNull();
    }
}