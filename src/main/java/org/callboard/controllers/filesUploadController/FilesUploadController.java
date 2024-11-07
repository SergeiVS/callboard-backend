package org.callboard.controllers.filesUploadController;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.controllers.api.FilesUploadControllerInterface;
import org.callboard.dto.StandardResponse;
import org.callboard.services.fileUploadServices.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
@Slf4j
public class FilesUploadController implements FilesUploadControllerInterface {

    private final FileUploadService fileUploadService;
    @RolesAllowed("ADMIN")
    @PostMapping
    public ResponseEntity<StandardResponse> uploadFile(@RequestParam("files") MultipartFile file) throws IOException {
        log.info("File name: "+ file.getOriginalFilename());
        return ResponseEntity.ok(new StandardResponse(fileUploadService.uploadFile(file)));
    }


}
