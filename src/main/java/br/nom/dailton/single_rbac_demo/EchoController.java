package br.nom.dailton.single_rbac_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EchoController {

    @GetMapping(value = "/echo/{input}")
    public Map<String, String> echo(@PathVariable("input") String input) {
        return Map.of("echo", input);
    }

    @GetMapping(value = "/s3/list/{bucketName}")
    public Map<String, Long> listBucketObjects(@PathVariable String bucketName) {
        try (S3Client s3Client = S3Client.builder()
//                .credentialsProvider(ProfileCredentialsProvider.builder()
//                        .profileName("s3_role_demo_user")
//                        .build())
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .region(Region.US_EAST_1)
                .build()) {
            ListObjectsV2Response listObjectsV2Response;
            listObjectsV2Response = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .build());
            return listObjectsV2Response.contents().stream()
                .collect(Collectors.toMap(S3Object::key, S3Object::size, (a, b) -> a));
        }
    }

}
