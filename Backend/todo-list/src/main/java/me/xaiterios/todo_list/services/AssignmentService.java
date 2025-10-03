package me.xaiterios.todo_list.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.xaiterios.todo_list.domain.Assignment;
import me.xaiterios.todo_list.domain.AssignmentStatus;
import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
import me.xaiterios.todo_list.domain.Response.AssignmentResponse;
import me.xaiterios.todo_list.exceptions.AssignmentNotFoundException;
import me.xaiterios.todo_list.exceptions.InvalidAssignmentRequestException;
import me.xaiterios.todo_list.repository.AssignmentRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService{
    private final AssignmentRepository assignmentRepository;

    private AssignmentResponse mapToAssignmentResponse(Assignment assignment){
        return AssignmentResponse.builder()
        .id(assignment.getId())
        .title(assignment.getTitle())
        .assignmentStatus(assignment.getAssignmentStatus())
        .build();
    }

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

    @Override
    public List<AssignmentResponse> GetAllAssignments() {
        return assignmentRepository.findAll().stream().map(this::mapToAssignmentResponse).toList();
    }

    @Override
    public List<AssignmentResponse> GetAllToDoAssignments() {
        return assignmentRepository.findAllByAssignmentStatus(AssignmentStatus.ToDo).stream().map(this::mapToAssignmentResponse).toList();
    }

    @Override
    public List<AssignmentResponse> GetAllInProgressAssignments() {
        return assignmentRepository.findAllByAssignmentStatus(AssignmentStatus.InProgress).stream().map(this::mapToAssignmentResponse).toList();
    }

    @Override
    public List<AssignmentResponse> GetAllCompletedAssignments() {
        return assignmentRepository.findAllByAssignmentStatus(AssignmentStatus.Completed).stream().map(this::mapToAssignmentResponse).toList();
    }

    @Override
    @Transactional
    public AssignmentResponse UpdateAssignment(String id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new AssignmentNotFoundException("Assignment with id " + id + " does not exist"));

        switch (assignment.getAssignmentStatus()) {
            case ToDo:
                assignment.setAssignmentStatus(AssignmentStatus.InProgress);
                break;
            case InProgress:
                assignment.setAssignmentStatus(AssignmentStatus.Completed);
                break;
            case Completed:
                assignmentRepository.deleteById(id);
                break;
        }

        return mapToAssignmentResponse(assignment);
    }
}
