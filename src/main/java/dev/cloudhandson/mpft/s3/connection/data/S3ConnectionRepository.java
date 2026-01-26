package dev.cloudhandson.mpft.s3.connection.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface S3ConnectionRepository extends ReactiveCrudRepository<S3ConnectionEntity, Long> {

    Mono<Boolean> existsByConnectionName(String connectionName);

    Mono<S3ConnectionEntity> findByConnectionId(UUID connectionId);

    Flux<S3ConnectionEntity> findAllBy(Pageable pageable);
}
