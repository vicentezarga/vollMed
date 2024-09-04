package med.voll.api.mensageria.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SQSConfiguration {

	@Value("${cloud.aws.sqs}")
	private String urlFila;

	@Value("${cloud.aws.credentials.accessKey}")
	private String acess;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secret;

	@Bean
	public SqsTemplate sqsTemplate() {
		return SqsTemplate.builder().sqsAsyncClient(this.sqsAsyncClient())
				.configure(option -> option
				.defaultQueue(urlFila)
				.defaultMaxNumberOfMessages(10)
				.defaultPollTimeout(Duration.ofSeconds(10)))
				.build();
	}

	@Bean
	public SqsAsyncClient sqsAsyncClient() {

		return SqsAsyncClient.builder().credentialsProvider(StaticCredentialsProvider.create(getCredencial())).build();
	}

	@Bean
	public AwsCredentials getCredencial() {

		AwsBasicCredentials awsCredencial = AwsBasicCredentials.create(acess, secret);

		return awsCredencial;
	}
}
