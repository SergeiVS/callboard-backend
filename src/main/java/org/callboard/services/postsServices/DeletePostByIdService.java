package org.callboard.services.postsServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.Request;
import org.callboard.dto.Response;
import org.callboard.dto.StandardIntRequest;
import org.callboard.dto.StandardResponse;
import org.callboard.services.StandardServiceInterface;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostByIdService implements StandardServiceInterface<StandardResponse, StandardIntRequest> {

    private final PostRepositoryService postRepositoryService;

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public StandardResponse execute(StandardIntRequest request) throws Exception {
        postRepositoryService.deleteBiId(Long.valueOf(request.getParameter()));
        return new StandardResponse("Post with id " + request.getParameter() + " deleted successfully");
    }
}
