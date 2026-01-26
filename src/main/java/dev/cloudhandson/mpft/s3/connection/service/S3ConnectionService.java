package dev.cloudhandson.mpft.s3.connection.service;

import dev.cloudhandson.mpft.s3.connection.api.model.S3ConnectionRequest;
import dev.cloudhandson.mpft.s3.model.S3ConnectionRest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface S3ConnectionService {

    Mono<S3ConnectionRest> createS3Connection(Mono<S3ConnectionRequest> s3ConnectionRequestMono);

    Mono<S3ConnectionRest> getS3Connection(UUID connectionId);

    Flux<S3ConnectionRest> getS3ConnectionList(int page, int limit);

    Mono<S3ConnectionRest> updateS3Connection(UUID connectionId, Mono<S3ConnectionRequest> s3ConnectionRequestMono);

    Mono<Void> deleteS3Connection(UUID connectionId);
}

