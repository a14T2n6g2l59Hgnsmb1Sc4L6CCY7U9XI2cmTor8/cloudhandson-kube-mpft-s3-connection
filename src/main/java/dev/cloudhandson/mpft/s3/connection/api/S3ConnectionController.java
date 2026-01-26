package dev.cloudhandson.mpft.s3.connection.api;

import dev.cloudhandson.mpft.s3.connection.api.model.S3ConnectionRequest;
import dev.cloudhandson.mpft.s3.connection.service.S3ConnectionService;
import dev.cloudhandson.mpft.s3.model.S3ConnectionRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

import static dev.cloudhandson.mpft.s3.connection.common.constant.ApiConstants.API_V1_S3_CONNECTIONS;

@RestController
@RequestMapping(API_V1_S3_CONNECTIONS)
public class S3ConnectionController {

    private final S3ConnectionService s3ConnectionService;

    private static final Logger LOGGER = LogManager.getLogger(S3ConnectionController.class);

    public S3ConnectionController(S3ConnectionService s3ConnectionService) {
        this.s3ConnectionService = s3ConnectionService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<S3ConnectionRest>> createS3Connection(@RequestBody Mono<S3ConnectionRequest> s3ConnectionRequestMono) {
        LOGGER.info("Incoming Create S3 Connection request");
        return s3ConnectionService
            .createS3Connection(s3ConnectionRequestMono)
            .map(s3ConnectionRest -> ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(API_V1_S3_CONNECTIONS + "/" + s3ConnectionRest.getConnectionId()))
                .body(s3ConnectionRest));
    }

    @GetMapping(path = "/{connectionId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<S3ConnectionRest>> getS3Connection(@PathVariable("connectionId") UUID connectionId) {
        LOGGER.info("Incoming Get S3 Connection request");
        return s3ConnectionService
            .getS3Connection(connectionId)
            .map(s3ConnectionRest -> ResponseEntity
                .status(HttpStatus.OK)
                .location(URI.create(API_V1_S3_CONNECTIONS + "/" + s3ConnectionRest.getConnectionId()))
                .body(s3ConnectionRest)
            );
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<S3ConnectionRest> getS3ConnectionList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "50") int limit) {
        LOGGER.info("Incoming Get S3 Connection List request with page: {}, limit: {}", page, limit);
        return s3ConnectionService
            .getS3ConnectionList(page, limit);
    }

    @PutMapping(value = "/{connectionId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<S3ConnectionRest>> updateS3Connection(@PathVariable("connectionId") UUID connectionId, @RequestBody Mono<S3ConnectionRequest> s3ConnectionRequestMono) {
        LOGGER.info("Incoming Update S3 Connection request for S3 Connection with connectionId: {}", connectionId);
        return s3ConnectionService
            .updateS3Connection(connectionId, s3ConnectionRequestMono)
            .map(s3ConnectionRest -> ResponseEntity
                .status(HttpStatus.OK)
                .location(URI.create(API_V1_S3_CONNECTIONS + "/" + s3ConnectionRest.getConnectionId()))
                .body(s3ConnectionRest));
    }

    @DeleteMapping(value = "/{connectionId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<Object>> deleteS3Connection(@PathVariable("connectionId") UUID connectionId) {
        LOGGER.info("Incoming Delete S3 Connection request");
        return s3ConnectionService
            .deleteS3Connection(connectionId)
            .thenReturn(ResponseEntity
                .status(HttpStatus.OK)
                .build());
    }
}
