package me.xaiterios.todo_list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.xaiterios.todo_list.domain.Assignment;
import me.xaiterios.todo_list.domain.AssignmentStatus;
import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
import me.xaiterios.todo_list.domain.Response.AssignmentResponse;
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

    @Test
    public void testGetAllAssignments(){
        List<Assignment> assignments = new ArrayList<>();

        Assignment assignmentRequest1 = Assignment.builder()
        .title("Title")
        .build();

        Assignment assignmentRequest2 = Assignment.builder()
        .title("Title2")
        .build();

        assignments.add(assignmentRequest1);
        assignments.add(assignmentRequest2);

        when(assignmentRepository.findAll()).thenReturn(assignments);

        List<AssignmentResponse> assignmentResponses = assignmentService.GetAllAssignments();

        assertEquals(2, assignmentResponses.size());
    } 

    @Test
    public void testGetAllToDoAssignments(){
        List<Assignment> assignments = new ArrayList<>();

        Assignment assignment1 = Assignment.builder()
        .title("Title")
        .assignmentStatus(AssignmentStatus.ToDo)
        .build();

        Assignment assignment2 = Assignment.builder()
        .title("Title2")
        .assignmentStatus(AssignmentStatus.ToDo)
        .build();

        assignments.add(assignment1);
        assignments.add(assignment2);

        when(assignmentRepository.findAllByAssignmentStatus(AssignmentStatus.ToDo)).thenReturn(assignments);

        List<AssignmentResponse> assignmentResponses = assignmentService.GetAllToDoAssignments();

        assertEquals(2, assignmentResponses.size());
    }

    @Test
    public void testGetAllInProgressAssignments(){
        List<Assignment> assignments = new ArrayList<>();

        Assignment assignment1 = Assignment.builder()
        .title("Title")
        .assignmentStatus(AssignmentStatus.InProgress)
        .build();

        Assignment assignment2 = Assignment.builder()
        .title("Title2")
        .assignmentStatus(AssignmentStatus.InProgress)
        .build();

        assignments.add(assignment1);
        assignments.add(assignment2);

        when(assignmentRepository.findAllByAssignmentStatus(AssignmentStatus.InProgress)).thenReturn(assignments);

        List<AssignmentResponse> assignmentResponses = assignmentService.GetAllInProgressAssignments();

        assertEquals(2, assignmentResponses.size());
        assertTrue(assignmentResponses.get(0).getAssignmentStatus() == AssignmentStatus.InProgress);
        assertTrue(assignmentResponses.get(1).getAssignmentStatus() == AssignmentStatus.InProgress);
    }

    @Test
    public void testGetAllCompletedAssignments(){
        List<Assignment> assignments = new ArrayList<>();

        Assignment assignment1 = Assignment.builder()
        .title("Title")
        .assignmentStatus(AssignmentStatus.Completed)
        .build();

        Assignment assignment2 = Assignment.builder()
        .title("Title2")
        .assignmentStatus(AssignmentStatus.Completed)
        .build();

        assignments.add(assignment1);
        assignments.add(assignment2);

        when(assignmentRepository.findAllByAssignmentStatus(AssignmentStatus.Completed)).thenReturn(assignments);

        List<AssignmentResponse> assignmentResponses = assignmentService.GetAllCompletedAssignments();

        assertEquals(2, assignmentResponses.size());
        assertTrue(assignmentResponses.get(0).getAssignmentStatus() == AssignmentStatus.Completed);
        assertTrue(assignmentResponses.get(1).getAssignmentStatus() == AssignmentStatus.Completed);
    }
}
