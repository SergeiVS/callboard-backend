package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.services.StandardServiceInterface;
import org.callboard.services.userServices.UserRepositoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindUsersPostsService implements StandardServiceInterface<PostListResponse, StandardStringRequest> {

    private final PostRepositoryService postRepositoryService;
    private final UserRepositoryService userRepositoryService;
    private final PostResponseListMapper mapper;

    @Override
    public PostListResponse execute(StandardStringRequest request) throws Exception {
        Integer userId = userRepositoryService.findUserIdByEmail(request.getParameter());
        return new PostListResponse(
                mapper.mapPostsListToPostResponseList(postRepositoryService.findByUserId(userId)));
    }
}
