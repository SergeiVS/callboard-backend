package org.callboard.services.postsServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostCreateSuccessResponse;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.mappers.PostMappers;
import org.callboard.services.StandardServiceInterface;
import org.callboard.services.fileUploadServices.FileUploadService;
import org.callboard.services.subjectService.SubjectRepositoryService;
import org.callboard.services.userServices.UserRepositoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePostService implements StandardServiceInterface <PostCreateSuccessResponse, NewPostRequest>{

    private final PostRepositoryService postRepoService;
    private final FileUploadService fileUploadService;
    private final UserRepositoryService userRepoService;
    private final SubjectRepositoryService subjectRepoService;

    @Transactional
    public PostCreateSuccessResponse execute(NewPostRequest request) throws IOException {

        Post postForSave = getPostForSave(request);

        Post savedPost = postRepoService.save(postForSave);

        return new PostCreateSuccessResponse(
                savedPost.getPostId(),
                "Your post is successfully added under id: " + savedPost.getPostId());
    }


    private @NotNull Post getPostForSave(NewPostRequest request) throws IOException {

        Post post = new Post();
                                    post.setSubject(extractSubjectFromRequest(request));
        setPhotolinkToPost(request.getImage(), post);
        setUserToPost(request, post);
        post.setHeader(request.getHeader());
        post.setDescription(request.getDescription());
        return postRepoService.save(post);
    }

    private void setUserToPost(NewPostRequest request, Post post) {

        User userForSave = userRepoService.findUserById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User with id: " + request.getUserId() + " not found"));

        post.setUser(userForSave);
    }

    private void setPhotolinkToPost(MultipartFile file, Post post) throws IOException {

        if (file != null) {
            String photoLink = fileUploadService.uploadFile(file);
            post.setPhotoLink(photoLink);
        } else {
            post.setPhotoLink("N/A");
        }
    }

    private Subject extractSubjectFromRequest(NewPostRequest request) {
        Subject subject = subjectRepoService.findByName(request.getSubject())
                .orElseThrow(() -> new NotFoundException("Subject: " + request.getSubject() + " not found"));
        return subject;
    }
}
