package org.callboard.services.postsServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
public class UpdatePostService implements PostServiceInterface<UpdatePostRequest>{

    private final PostRepositoryService postRepoService;
    private final PostMappers postMappers;
    private final SubjectRepositoryService subjectRepoService;

    @Transactional
    @Override
    public ResponseEntity<List<PostResponse>> execute(UpdatePostRequest request) {
        Post postForSave = savePost(request);
        Post post = postRepoService.save(postForSave);
        List<PostResponse> rerponseList = getPostResponses(post);
        return new ResponseEntity<>(rerponseList, HttpStatus.OK);
    }

    private @NotNull List<PostResponse> getPostResponses(Post post) {
        PostResponse response = postMappers.toPostResponse(post);
        response.setSubject(post.getSubject().getName());
        List<PostResponse> rerponseList = new ArrayList<>();
        rerponseList.add(response);
        return rerponseList;
    }

    private @NotNull Post savePost(UpdatePostRequest request) {
        Post postForSave = new Post();

        if(postRepoService.existsById(request.getPostId())) {
           postForSave = postMappers.toPost(request);
        }

        Subject subjectForSave = subjectRepoService.findByName(request.getSubject())
                .orElseThrow(()->new NotFoundException(STR."Subject: \{request.getSubject()} not found"));

        postForSave.setSubject(subjectForSave);
        return postForSave;
    }


}
