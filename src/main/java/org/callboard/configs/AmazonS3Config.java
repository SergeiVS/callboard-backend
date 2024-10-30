package org.callboard.configs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AmazonS3Config {


    @Bean

    public String jwtToken(S3ConfigurationProperties S3ConfigProperties) {
        return S3ConfigProperties.getJwtSecret();
    }

    @Bean
    AmazonS3 amazonS3(S3ConfigurationProperties S3ConfigProperties) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                S3ConfigProperties.getValut_access_key(),
                S3ConfigProperties.getValut_secret_key()
        );

        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                S3ConfigProperties.getEndpoint(),
                S3ConfigProperties.getRegion()
        );

        AmazonS3ClientBuilder amazonS3Builder = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials));

        amazonS3Builder.setEndpointConfiguration(endpointConfiguration);

        return amazonS3Builder.build();
    }

}
