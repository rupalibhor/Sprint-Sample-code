// This is a configuration class for setting up AWS SQS (Simple Queue Service) in a Spring application. It defines beans for the SQS client and template, using credentials and region information from the application's properties. 
package com.rb.sp.springsqs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;


@Configuration
public class AwsSQSConfig {

  // you can also use the default credential provider chain, which will look for credentials in the following order:
  // Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
  @Value("${spring.cloud.aws.credentials.access-key}")
  private String accessKey;

  // you can also use the default credential provider chain, which will look for credentials in the following order:
  // Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
  @Value("${spring.cloud.aws.credentials.secret-key}")
  private String secretKey;

  // you can also use the default region provider chain, which will look for the region in the following order:
  // Environment Variables - AWS_REGION
  @Value("${spring.cloud.aws.region.static}")
  private String region;

  // you can also use the default client configuration, which will look for the following properties:
  // spring.cloud.aws.sqs.client.max-connections
  @Bean
  SqsAsyncClient sqsAsyncClient(){
    return SqsAsyncClient
      .builder()
      .region(Region.of(region))
      .credentialsProvider(StaticCredentialsProvider
        .create(AwsBasicCredentials.create(accessKey, secretKey)))
      .build();
   // add more Options
  }

// you can also use the default client configuration, which will look for the following properties:
// spring.cloud.aws.sqs.client.max-connections
  @Bean
  public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient){
      return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build();
  }
}