package org.callboard.services.fileUploadServices;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.configs.AmazonS3Config;
import org.callboard.configs.S3ConfigurationProperties;
import org.callboard.dto.StandardResponse;
import org.callboard.entities.FileInfo;
import org.callboard.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadService {
    private final AmazonS3 s3;
    private final FileInfoRepository fileInfoRepository;

    public StandardResponse uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String extension = "";
        if (fileName != null && !fileName.isEmpty()) {
            extension = fileName.substring(fileName.lastIndexOf("."));
        } else {
            throw new IllegalArgumentException("Invalid file name");
        }
        String newFileName = UUID.randomUUID() + "." + extension;
        InputStream inputStream = file.getInputStream();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        PutObjectRequest request = new PutObjectRequest(
                "demo-shop-files.help-app-images",
                newFileName,
                inputStream,
                metadata
        )
                .withCannedAcl(CannedAccessControlList.PublicRead);

        request.getRequestClientOptions().setReadLimit(2000000);
        s3.putObject(request);

        String link = s3.getUrl("demo-shop-files.help-app-images", "help-app-images/" + newFileName).toString();

        FileInfo fileInfo = FileInfo.builder()
                .link(link)
                .build();
        fileInfoRepository.save(fileInfo);
        return new StandardResponse(link);
    }


}
