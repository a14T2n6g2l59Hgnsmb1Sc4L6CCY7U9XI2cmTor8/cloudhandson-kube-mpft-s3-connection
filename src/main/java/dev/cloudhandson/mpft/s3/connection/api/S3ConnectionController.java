package dev.cloudhandson.mpft.s3.connection.api;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static dev.cloudhandson.mpft.s3.connection.common.constant.ApiConstants.API_V1_S3_CONNECTIONS;

@RestController
@RequestMapping(API_V1_S3_CONNECTIONS)
public class S3ConnectionController {

    @PostMapping
    public Mono<String> createS3Connection() {
        return Mono.just("Incoming Create S3 Connection request");
    }

    @GetMapping
    public Mono<String> getS3Connection() {
        return Mono.just("Incoming Get S3 Connection request");
    }

    @PutMapping
    public Mono<String> updateS3Connection() {
        return Mono.just("Incoming Update S3 Connection request");
    }

    @DeleteMapping
    public Mono<String> deleteS3Connection() {
        return Mono.just("Incoming Delete S3 Connection request");
    }
}
