package labs.communication.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import labs.communication.api.resource.CommunicationInput;
import labs.communication.domain.entity.Communication;
import labs.communication.domain.service.CommunicationCancellation;
import labs.communication.domain.service.CommunicationQuery;
import labs.communication.domain.service.CommunicationScheduling;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("communication")
@RequiredArgsConstructor
public class CommunicationController {

    private final ModelMapper modelMapper;

    private final CommunicationScheduling scheduling;

    private final CommunicationQuery query;

    private final CommunicationCancellation cancellation;

    @PostMapping
    @Operation(summary = "Schedule a communication.")
    public ResponseEntity<Communication> schedule(@Validated @RequestBody CommunicationInput input) {
        var communication = scheduling.execute(modelMapper.map(input, Communication.class));
        return ResponseEntity.ok(communication);
    }

    @GetMapping(path = {"/{id}"})
    @Operation(summary = "Get a communication status.")
    public ResponseEntity<Communication> query(@PathVariable Long id) {
        var communication = query.execute(id);
        return ResponseEntity.of(communication);
    }

    @DeleteMapping(path = {"/{id}"})
    @Operation(summary = "Cancel a communication.")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        cancellation.execute(id);
        return ResponseEntity.ok().build();
    }

}
