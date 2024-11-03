package org.callboard.services.userServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardIntRequest;
import org.callboard.dto.StandardResponse;
import org.callboard.exceptions.NotFoundException;
import org.callboard.services.StandardServiceInterface;
import org.callboard.services.postsServices.PostRepositoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements StandardServiceInterface<StandardResponse, StandardIntRequest> {

    private final UserRepositoryService userRepositoryService;
    private final PostRepositoryService postRepositoryService;

    /**
     * @param request
     * @return Standard response
     * @throws Exception
     */
    @Transactional
    @Override
    public StandardResponse execute(StandardIntRequest request) throws NotFoundException {

        postRepositoryService.deleteByUserId(request.getParameter());
        userRepositoryService.deleteUserById(request.getParameter());

        return new StandardResponse(
                "User with id: " + request.getParameter() + " deleted successfully");
    }
}
