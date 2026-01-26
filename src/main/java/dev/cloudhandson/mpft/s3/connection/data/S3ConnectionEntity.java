package dev.cloudhandson.mpft.s3.connection.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(value = "S3_CONNECTION", schema = "S3_CONNECTION")
public class S3ConnectionEntity {

    @Id
    @Column("ID")
    private Long id;

    @Column("CONNECTION_ID")
    private UUID connectionId;

    @Column("CONNECTION_NAME")
    private String connectionName;

    @Column("SESSION_NAME")
    private String sessionName;

    @Column("REGION")
    private String region;

    @Column("IAM_ROLE_ARN")
    private String iamRoleArn;

    @Column("ENCRYPTED_ACCESS_KEY_ID")
    private String encryptedAccessKeyId;

    @Column("ENCRYPTED_SECRET_ACCESS_KEY")
    private String encryptedSecretAccessKey;

    public S3ConnectionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(UUID connectionId) {
        this.connectionId = connectionId;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIamRoleArn() {
        return iamRoleArn;
    }

    public void setIamRoleArn(String iamRoleArn) {
        this.iamRoleArn = iamRoleArn;
    }

    public String getEncryptedAccessKeyId() {
        return encryptedAccessKeyId;
    }

    public void setEncryptedAccessKeyId(String encryptedAccessKeyId) {
        this.encryptedAccessKeyId = encryptedAccessKeyId;
    }

    public String getEncryptedSecretAccessKey() {
        return encryptedSecretAccessKey;
    }

    public void setEncryptedSecretAccessKey(String encryptedSecretAccessKey) {
        this.encryptedSecretAccessKey = encryptedSecretAccessKey;
    }

    @Override
    public String toString() {
        return "S3ConnectionEntity{" +
            "id=" + id +
            ", connectionId=" + connectionId +
            ", connectionName='" + connectionName + '\'' +
            ", sessionName='" + sessionName + '\'' +
            ", region='" + region + '\'' +
            ", iamRoleArn='" + iamRoleArn + '\'' +
            '}';
    }
}
