package dev.cloudhandson.mpft.s3.connection.api.model;

public class S3ConnectionRequest {

    private String connectionName;

    private String sessionName;

    private String region;

    private String iamRoleArn;

    private String accessKeyId;

    private String secretAccessKey;

    public S3ConnectionRequest() {
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

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    @Override
    public String toString() {
        return "S3ConnectionRequest{" +
            "connectionName='" + connectionName + '\'' +
            ", sessionName='" + sessionName + '\'' +
            ", region='" + region + '\'' +
            ", iamRoleArn='" + iamRoleArn + '\'' +
            '}';
    }
}
