package org.callboard.services.postsServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardIntRequest;
import org.callboard.dto.StandardResponse;
import org.callboard.services.StandardServiceInterface;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostsByUserId implements StandardServiceInterface<StandardResponse, StandardIntRequest> {
    private final PostRepositoryService repositoryService;

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public StandardResponse execute(StandardIntRequest request) throws Exception {
        repositoryService.deleteByUserId(request.getParameter());
        return new StandardResponse("All posts of User with id: " + request.getParameter() + "were deleted.");
    }
}
