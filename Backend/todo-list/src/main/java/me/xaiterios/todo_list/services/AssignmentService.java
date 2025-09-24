package me.xaiterios.todo_list.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.xaiterios.todo_list.domain.Assignment;
import me.xaiterios.todo_list.domain.AssignmentStatus;
import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
import me.xaiterios.todo_list.exceptions.InvalidAssignmentRequestException;
import me.xaiterios.todo_list.repository.AssignmentRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssingmentService{
    private final AssignmentRepository assignmentRepository;

    @Override
    public void CreateAssingment(AssignmentRequest assignmentRequest) {
        String title = assignmentRequest.getTitle().trim();
        if (title == null || title.length() < 1){
            throw new InvalidAssignmentRequestException("Title can not be empty");
        }

        Assignment assignment = Assignment.builder()
        .id(UUID.randomUUID().toString())
        .title(title)
        .assignmentStatus(AssignmentStatus.ToDo)
        .build();

        assignmentRepository.save(assignment);
    }
}
