package labs.communication.api.resource;

import labs.communication.domain.base.CommunicationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class CommunicationInput {

    @NotNull
    private ZonedDateTime time;

    @NotBlank
    private String receiver;

    @NotBlank
    private String message;

    @NotNull
    private CommunicationType type;

}
