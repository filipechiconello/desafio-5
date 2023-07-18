package br.com.superatecnologia.managementapi.services.impl;


import br.com.superatecnologia.managementapi.dtos.requests.EmailRequestDTO;
import br.com.superatecnologia.managementapi.services.NotificationService;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Override
    public void notificationEmail(EmailRequestDTO emailRequestDTO) {
        log.info("notification by email: {}", emailRequestDTO.getTo());

        AWSCredentials awsCred = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider cred = new AWSStaticCredentialsProvider(awsCred);

        AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(cred)
                        .withRegion(Regions.US_EAST_1).build();

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(emailRequestDTO.getTo()))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withText(new Content()
                                        .withCharset("UTF-8").withData(emailRequestDTO.getContent())))
                        .withSubject(new Content()
                                .withCharset("UTF-8").withData(emailRequestDTO.getSubject())))
                .withSource(emailRequestDTO.getFrom());

        client.sendEmail(request);
    }
}
