package com.example.amazons3sdksample;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

public class AmazonS3Service {

    public CompleteMultipartUploadResponse upload(RequestBody data, String filePath, String bucketName){

        Region region = Region.AP_NORTHEAST_2; // region

        ObjectCannedACL acl = ObjectCannedACL.PUBLIC_READ; // 읽기 공개 옵션

        S3Client s3Client = S3Client.builder().region( region ).build();

        CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
                .bucket(bucketName).key( filePath )
                .acl(acl)
                .build();

        CreateMultipartUploadResponse response = s3Client.createMultipartUpload(createMultipartUploadRequest);

        UploadPartRequest uploadPartRequest1 = UploadPartRequest.builder().bucket(bucketName).key( filePath )
                .uploadId( response.uploadId() )
                .partNumber(1).build();

        String etag1 = s3Client.uploadPart(uploadPartRequest1, data).eTag();

        CompletedPart part1 = CompletedPart.builder().partNumber(1).eTag(etag1).build();

        CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder().parts(part1).build();
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                CompleteMultipartUploadRequest.builder().bucket(bucketName).key( filePath ).uploadId( response.uploadId() )
                        .multipartUpload(completedMultipartUpload)
                        .build();

        return s3Client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    public DeleteObjectResponse delete(String filePath, String bucketName){

        Region region = Region.AP_NORTHEAST_2; // region

        ObjectCannedACL acl = ObjectCannedACL.PUBLIC_READ; // 읽기 공개 옵션

        S3Client s3Client = S3Client.builder().region( region ).build();

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket( bucketName ).key( filePath ).build();

        return s3Client.deleteObject(deleteObjectRequest);
    }
}
