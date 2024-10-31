package org.callboard.controllers.filesUploadController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.services.fileUploadServices.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
@Slf4j
public class FilesUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<StandardResponse> uploadFile(@RequestParam("files") MultipartFile file) throws IOException {
        log.info("File name: "+ file.getOriginalFilename());
        return ResponseEntity.ok(fileUploadService.uploadFile(file));
    }
}
