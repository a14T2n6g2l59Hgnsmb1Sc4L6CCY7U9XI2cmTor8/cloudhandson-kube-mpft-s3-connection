package dev.cloudhandson.mpft.s3.connection.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static dev.cloudhandson.mpft.s3.connection.common.constant.ApiConstants.API_V1_S3_CONNECTIONS;

@RestController
@RequestMapping(API_V1_S3_CONNECTIONS)
public class S3ConnectionController {

    private static final Logger LOGGER = LogManager.getLogger(S3ConnectionController.class);

    @PostMapping
    public Mono<String> createS3Connection() {
        String message = "Incoming Create S3 Connection request";
        LOGGER.info(message);
        return Mono.just(message);
    }

    @GetMapping
    public Mono<String> getS3Connection() {
        String message = "Incoming Get S3 Connection request";
        LOGGER.info(message);
        return Mono.just(message);
    }

    @PutMapping
    public Mono<String> updateS3Connection() {
        String message = "Incoming Update S3 Connection request";
        LOGGER.info(message);
        return Mono.just(message);
    }

    @DeleteMapping
    public Mono<String> deleteS3Connection() {
        String message = "Incoming Delete S3 Connection request";
        LOGGER.info(message);
        return Mono.just(message);
    }
}
