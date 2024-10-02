package org.callboard.mappers;

import org.callboard.dto.subjectDto.SubjectResponse;
import org.callboard.entities.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMappers {
    SubjectResponse toSubjectResponse(Subject subject);
    Subject toSubject(SubjectResponse subjectResponse);
}
