package com.rest;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.dto.PresignedUrlDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

/**
 * Created by aautushk on 12/16/2015.
 */
@Component
@RestController
@RequestMapping("/file-manager")
public class FileManagementRest {
    @Value("${aws.s3.accessKeyId}")
    private String s3accessKeyId;

    @Value("${aws.s3.secretKey}")
    private String s3SecretKey;

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    @RequestMapping(value = "/generatePresignedUrlForS3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public PresignedUrlDto generatePresignedUrlForS3( @RequestParam(value = "objectKey") String objectKey) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3accessKeyId, s3SecretKey);
        AmazonS3 s3Client = new AmazonS3Client(awsCreds);
        PresignedUrlDto presignedUrlDto = new PresignedUrlDto("");
        try {
                java.util.Date expiration = new java.util.Date();
            long milliSeconds = expiration.getTime();
            milliSeconds += 1000 * 60 * 60; // Add 1 hour.
            expiration.setTime(milliSeconds);

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(s3BucketName, objectKey);
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(expiration);

            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

            presignedUrlDto.setPresignedUrl(url.toString());

            return presignedUrlDto;
        } catch (AmazonServiceException exception) {
            System.out.println("Caught an AmazonServiceException, " +
                    "which means your request made it " +
                    "to Amazon S3, but was rejected with an error response " +
                    "for some reason.");
            System.out.println("Error Message: " + exception.getMessage());
            System.out.println("HTTP  Code: "    + exception.getStatusCode());
            System.out.println("AWS Error Code:" + exception.getErrorCode());
            System.out.println("Error Type:    " + exception.getErrorType());
            System.out.println("Request ID:    " + exception.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, " +
                    "which means the client encountered " +
                    "an internal error while trying to communicate" +
                    " with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

        return presignedUrlDto;
    }
}