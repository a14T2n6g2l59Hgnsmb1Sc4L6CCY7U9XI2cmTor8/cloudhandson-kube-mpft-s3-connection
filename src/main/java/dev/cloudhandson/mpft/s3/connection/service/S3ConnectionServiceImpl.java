package dev.cloudhandson.mpft.s3.connection.service;

import dev.cloudhandson.mpft.s3.connection.api.model.S3ConnectionRequest;
import dev.cloudhandson.mpft.s3.connection.common.exception.S3ConnectionAlreadyExistException;
import dev.cloudhandson.mpft.s3.connection.common.util.MapperUtil;
import dev.cloudhandson.mpft.s3.connection.data.S3ConnectionEntity;
import dev.cloudhandson.mpft.s3.connection.data.S3ConnectionRepository;
import dev.cloudhandson.mpft.s3.model.S3ConnectionNotFoundException;
import dev.cloudhandson.mpft.s3.model.S3ConnectionRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class S3ConnectionServiceImpl implements S3ConnectionService {

    private final S3ConnectionRepository s3ConnectionRepository;

    private static final Logger LOGGER = LogManager.getLogger(S3ConnectionServiceImpl.class);

    public S3ConnectionServiceImpl(S3ConnectionRepository s3ConnectionRepository) {
        this.s3ConnectionRepository = s3ConnectionRepository;
    }

    @Override
    public Mono<S3ConnectionRest> createS3Connection(Mono<S3ConnectionRequest> s3ConnectionRequestMono) {
        return s3ConnectionRequestMono.flatMap(this::validateAndSaveS3Connection);
    }

    @Override
    public Mono<S3ConnectionRest> getS3Connection(UUID connectionId) {
        return getS3ConnectionByConnectionId(connectionId)
            .mapNotNull(s3ConnectionEntity -> MapperUtil.map(s3ConnectionEntity, S3ConnectionRest.class));
    }

    @Override
    public Flux<S3ConnectionRest> getS3ConnectionList(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return s3ConnectionRepository
            .findAllBy(pageable)
            .map(s3ConnectionEntity -> MapperUtil.map(s3ConnectionEntity, S3ConnectionRest.class));
    }

    @Override
    public Mono<S3ConnectionRest> updateS3Connection(UUID connectionId, Mono<S3ConnectionRequest> s3ConnectionRequestMono) {
        return getS3ConnectionByConnectionId(connectionId)
            .zipWith(s3ConnectionRequestMono)
            .flatMap(tuple -> {
                S3ConnectionEntity s3ConnectionEntity = tuple.getT1();
                S3ConnectionRequest s3ConnectionRequest = tuple.getT2();
                LOGGER.debug(s3ConnectionRequest);
                BeanUtils.copyProperties(s3ConnectionRequest, s3ConnectionEntity);
                return s3ConnectionRepository.save(s3ConnectionEntity);
            })
            .map(updatedS3ConnectionEntity -> {
                LOGGER.debug(updatedS3ConnectionEntity);
                S3ConnectionRest s3ConnectionRest = MapperUtil.map(updatedS3ConnectionEntity, S3ConnectionRest.class);
                LOGGER.debug(s3ConnectionRest);
                return s3ConnectionRest;
            });
    }

    @Override
    public Mono<Void> deleteS3Connection(UUID connectionId) {
        return getS3ConnectionByConnectionId(connectionId)
            .flatMap(s3ConnectionRepository::delete);
    }

    private Mono<S3ConnectionRest> validateAndSaveS3Connection(S3ConnectionRequest s3ConnectionRequest) {
        return s3ConnectionRepository
            .existsByConnectionName(s3ConnectionRequest.getConnectionName())
            .flatMap(exist -> {
                if (exist) {
                    return Mono.error(
                        new S3ConnectionAlreadyExistException("S3 Connection with Connection Name '" + s3ConnectionRequest.getConnectionName() + "' already exist")
                    );
                }
                return savesS3Connection(s3ConnectionRequest);
            });
    }

    private Mono<S3ConnectionRest> savesS3Connection(S3ConnectionRequest s3ConnectionRequest) {
        LOGGER.debug(s3ConnectionRequest);
        S3ConnectionEntity s3ConnectionEntity = MapperUtil.map(s3ConnectionRequest, S3ConnectionEntity.class);
        s3ConnectionEntity.setConnectionId(UUID.randomUUID());
        return s3ConnectionRepository
            .save(s3ConnectionEntity)
            .map(savedS3ConnectionEntity -> {
                LOGGER.debug(savedS3ConnectionEntity);
                return MapperUtil.map(savedS3ConnectionEntity, S3ConnectionRest.class);
            });
    }

    private Mono<S3ConnectionEntity> getS3ConnectionByConnectionId(UUID connectionId) {
        return s3ConnectionRepository
            .findByConnectionId(connectionId)
            .switchIfEmpty(
                Mono.error(new S3ConnectionNotFoundException("S3 Connection with connectionId " + connectionId + " does not exist"))
            );
    }
}
