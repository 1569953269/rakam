package org.rakam.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import io.airlift.configuration.Config;

/**
 * Created by buremba <Burak Emre Kabakcı> on 02/07/15 06:48.
 */
public class AWSConfig {
    private String accessKey;
    private String secretAccessKey;
    private String s3Bucket;
    private String eventStoreStreamName;


    public String getEventStoreStreamName() {
        return eventStoreStreamName;
    }

    @Config("event.store.kinesis.stream")
    public void setEventStoreStreamName(String eventStoreStreamName) {
        this.eventStoreStreamName = eventStoreStreamName;
    }

    @Config("aws.access_key")
    public AWSConfig setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    @Config("aws.event.s3_bucket")
    public AWSConfig setS3Bucket(String S3Bucket) {
        this.s3Bucket = S3Bucket;
        return this;
    }

    public String getS3Bucket() {
        return s3Bucket;
    }

    public String getAccessKey() {
        return accessKey;
    }

    @Config("aws.secret_access_key")
    public AWSConfig setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
        return this;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public StaticCredentialsProvider getCredentials() {
        return new StaticCredentialsProvider(new BasicAWSCredentials(getAccessKey(), getSecretAccessKey()));
    }
}
