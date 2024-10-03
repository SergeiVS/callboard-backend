package org.callboard.services.postsServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.mappers.PostMappers;
import org.callboard.services.subjectService.SubjectRepositoryService;
import org.callboard.services.userServices.UserRepositoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatePostService implements PostServiceInterface<NewPostRequest> {

    private final PostRepositoryService postRepoService;
    PostResponseListMapper postResponseListMapper;
    private final PostMappers postMappers;
    private final UserRepositoryService userRepoService;
    private final SubjectRepositoryService subjectRepoService;

    @Transactional
    @Override
    public ResponseEntity<List<PostResponse>> execute(NewPostRequest request) {

        Post postForSave = getPostForSave(request);

        Post savedPost = postRepoService.save(postForSave);

        List<PostResponse> postResponses = postResponseListMapper.mapPostToPostResponseList(savedPost);

        return new ResponseEntity<>(postResponses, HttpStatus.CREATED);
    }


    private @NotNull Post getPostForSave(NewPostRequest request) {

        Post postForSave = postMappers.toPost(request);

        Subject subject = subjectRepoService.findByName(request.getSubject())
                .orElseThrow(()->new NotFoundException(STR."Subject: \{request.getSubject()} not found"));

        postForSave.setSubject(subject);

        User userForSave = userRepoService.findUserById(request.getUserId())
                .orElseThrow(() -> new NotFoundException(STR."User with id: \{request.getUserId()} not found"));

        postForSave.setUser(userForSave);
        return postForSave;
    }
}
