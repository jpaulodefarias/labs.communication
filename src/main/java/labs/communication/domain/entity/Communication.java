package labs.communication.domain.entity;

import labs.communication.domain.state.CommunicationStatus;
import labs.communication.domain.type.CommunicationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Communication {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private ZonedDateTime time;

    @NotBlank
    private String receiver;

    @NotBlank
    private String message;

    @NotNull
    private CommunicationType type;

    @NotNull
    private CommunicationStatus status;

    public boolean isCancelable() {
        return CommunicationStatus.SCHEDULED.equals(status);
    }

}
