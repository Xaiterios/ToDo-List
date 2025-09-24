package me.xaiterios.todo_list;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.xaiterios.todo_list.domain.Assignment;
import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
import me.xaiterios.todo_list.exceptions.InvalidAssignmentRequestException;
import me.xaiterios.todo_list.repository.AssignmentRepository;
import me.xaiterios.todo_list.services.AssignmentService;

public class AssignmentServiceTests {
    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    @BeforeEach
    public void BeforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAssignment(){
        AssignmentRequest assignmentRequest = AssignmentRequest.builder()
        .title("Title")
        .build();

        assignmentService.CreateAssingment(assignmentRequest);

        verify(assignmentRepository, times(1)).save(any(Assignment.class));
    }

    @Test
    public void testCreateAssignmentInvalidTitleShouldThrowInvalidAssignmentRequestException(){
        AssignmentRequest assignmentRequest = AssignmentRequest.builder()
        .title("")
        .build();

        assertThrows(InvalidAssignmentRequestException.class, () -> assignmentService.CreateAssingment(assignmentRequest));
    
        verify(assignmentRepository, never()).save(any(Assignment.class));
    }
}
