package org.callboard.services.subjectService;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.Subject;
import org.callboard.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectRepositoryService {

    private final SubjectRepository subjectRepository;

    public boolean existsByName(String name) {
        return subjectRepository.existsByName(name);
    }
    public Optional<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }
}
