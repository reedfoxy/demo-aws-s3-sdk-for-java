package com.example.amazons3sdksample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import software.amazon.awssdk.core.sync.RequestBody;
import java.io.IOException;

@SpringBootTest
class AmazonS3SdkSampleApplicationTests {

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void fileUpload() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:static/test.jpg"); // test.jpg 이미지 가져옴

        if ( resource.exists() ) {

            AmazonS3Service amazonS3Service = new AmazonS3Service(); // amazon s3 서비스 생성

            RequestBody requestBody = RequestBody.fromFile( resource.getFile() );

            amazonS3Service.upload( requestBody , resource.getFilename(), "bonsookoo" ); // 파일 업로드
        } else {
            System.out.println(" file not exist ");
        }
    }

    @Test
    void fileDownload() {

        Resource resource = resourceLoader.getResource("classpath:static/test.jpg"); // test.jpg 이미지 가져옴

        if ( resource.exists() ) {

            AmazonS3Service amazonS3Service = new AmazonS3Service();
            amazonS3Service.delete( resource.getFilename() , "bonsookoo");
        }

    }
}
