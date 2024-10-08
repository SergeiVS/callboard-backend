package org.callboard.services.postsServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.postDto.UpdatePostRequest;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.exceptions.NotFoundException;
import org.callboard.mappers.PostMappers;
import org.callboard.services.subjectService.SubjectRepositoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdatePostService implements PostServiceInterface<PostResponse, UpdatePostRequest> {

    private final PostRepositoryService postRepoService;
    private final PostMappers postMappers;

    @Transactional
    @Override
    public PostResponse execute(UpdatePostRequest request) {

        Post post = savePost(request);

        Post savedPost = postRepoService.save(post);

        return postMappers.toPostResponse(savedPost);
    }


    private @NotNull Post savePost(UpdatePostRequest request) {
        Post postForSave = postRepoService.findById(request.getPostId())
                .orElseThrow(() -> new NotFoundException(STR."Post with id: \{request.getPostId()} not found"));
        setNewValuesForPost(request, postForSave);
        return postForSave;
    }

    private static void setNewValuesForPost(UpdatePostRequest request, Post postForSave) {

        if (request.getHeader() != null) {
            postForSave.setHeader(request.getHeader());
        }
        if (request.getDescription() != null) {
            postForSave.setDescription(request.getDescription());
        }
        if (request.getPhotoLink() != null) {
            postForSave.setPhotoLink(request.getPhotoLink());
        }
    }


}
