package labs.communication.api.controller;

import labs.communication.api.resource.CommunicationInput;
import labs.communication.domain.entity.Communication;
import labs.communication.domain.service.CommunicationScheduling;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("communication")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationScheduling scheduling;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Communication> schedule(@Validated @RequestBody CommunicationInput input) {
        var communication = scheduling.execute(modelMapper.map(input, Communication.class));
        return ResponseEntity.ok(communication);
    }

}
