package dev.cloudhandson.mpft.s3.connection.common.exception;

public class S3ConnectionAlreadyExistException extends RuntimeException {

    public S3ConnectionAlreadyExistException(String message) {
        super(message);
    }
}
