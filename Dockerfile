FROM amazoncorretto:21-alpine-jdk

RUN addgroup -S lnxUser && adduser -S lnxUser -G lnxUser && \
    mkdir -p /logs/S3Connection && chown -R lnxUser:lnxUser /logs

USER lnxUser:lnxUser

## To use this dependency
## mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib

COPY ${DEPENDENCY}/META-INF /app/META-INF

COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "dev.cloudhandson.mpft.s3.connection.S3ConnectionApplication"]