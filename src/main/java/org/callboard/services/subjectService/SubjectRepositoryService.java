package org.callboard.services.subjectService;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.Subject;
import org.callboard.exceptions.NotFoundException;
import org.callboard.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectRepositoryService {

    private final SubjectRepository subjectRepository;

    public boolean existsByName(String name) {
        return subjectRepository.existsByName(name);
    }

    public Subject findByName(String name) {
        return subjectRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Subject with name " + name + " not found"));
    }
}
