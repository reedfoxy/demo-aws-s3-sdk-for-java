# AWS S3 SDK 2.0 for Java

사용 방법은 https://velog.io/@reedfoxy/AWS-S3-SDK-2.0-for-Java 링크 참고

build.gradle
```
dependencies {
    implementation 'software.amazon.awssdk:s3:2.13.0'
```

AmazonS3Service.java

파일 업로드
```
/**
     * 파일 업로드
     * @param data 업로드 파일 데이터
     * @param filePath 아마존 S3 에 업로드 할 파일 경로
     * @param bucketName 아마존 S3 에 업로드 할 bucket 이름
     * @return 업로드 결과
     * @author bsk
     */
    public CompleteMultipartUploadResponse upload(RequestBody data, String filePath, String bucketName){
```

파일 삭제
```
/**
     * 파일 삭제
     * @param filePath 아마존 S3 에 업로된 파일 경로
     * @param bucketName 삭제 할 bucket 이름
     * @return 삭제 결과
     * @author bsk
     */
    public DeleteObjectResponse delete(String filePath, String bucketName){
```

AmazonS3SdkSampleApplicationTests.java

파일 업로드 테스트 코드
```
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
```

파일 삭제 테스트 코드
```
    @Test
    void fileDelete() {

        Resource resource = resourceLoader.getResource("classpath:static/test.jpg"); // test.jpg 이미지 가져옴

        if ( resource.exists() ) {

            AmazonS3Service amazonS3Service = new AmazonS3Service();
            amazonS3Service.delete( resource.getFilename() , "bonsookoo");
        }

    }
```