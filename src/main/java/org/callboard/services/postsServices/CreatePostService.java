package org.callboard.services.postsServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.entities.Post;
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
    private final PostMappers postMappers;
    private final UserRepositoryService userRepoService;
    private final SubjectRepositoryService subjectRepoService;

    @Transactional
    @Override
    public ResponseEntity<List<PostResponse>> execute(NewPostRequest request) {

        isSubjectExist(request);

        Post postForSave = getPostForSave(request);

        Post savedPost = postRepoService.save(postForSave);

        List<PostResponse> responseList = getResponseList(savedPost);

        return new ResponseEntity<>(responseList, HttpStatus.CREATED);
    }



    private @NotNull List<PostResponse> getResponseList(Post savedPost) {

        PostResponse postResponse = postMappers.toPostResponse(savedPost);
        List<PostResponse> responseList = new ArrayList<>();
        responseList.add(postResponse);
        return responseList;
    }

    private void isSubjectExist(NewPostRequest request) {
        if (!subjectRepoService.existsByName(request.getSubject())) {
            throw new NotFoundException(STR."Subject: \{request.getSubject()} not found");
        }
    }

    private @NotNull Post getPostForSave(NewPostRequest request) {

        Post postForSave = postMappers.toPost(request);
        User userForSave = userRepoService.findUserById(request.getUserId())
                .orElseThrow(() -> new NotFoundException(STR."User with id: \{request.getUserId()} not found"));
        postForSave.setUser(userForSave);
        return postForSave;
    }
}
