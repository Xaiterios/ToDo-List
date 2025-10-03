package me.xaiterios.todo_list;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.xaiterios.todo_list.controller.AssignmentController;
import me.xaiterios.todo_list.domain.AssignmentStatus;
import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
import me.xaiterios.todo_list.domain.Response.AssignmentResponse;
import me.xaiterios.todo_list.exceptions.InvalidAssignmentRequestException;
import me.xaiterios.todo_list.services.AssignmentService;

@WebMvcTest(AssignmentController.class)
public class AssignmentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AssignmentService assignmentService;

    @Test
    public void testCreateAssignment() throws Exception{
        AssignmentRequest assignmentRequest = AssignmentRequest.builder()
        .title("Title")
        .build();

        String assignmentString = objectMapper.writeValueAsString(assignmentRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/assignment")
        .contentType(MediaType.APPLICATION_JSON)
        .content(assignmentString))
        .andExpect(status().isCreated());
    }

    @Test
    public void testCreateAssignmentInvalidTitleShouldThrowInvalidAssignmentRequestException() throws Exception{
        AssignmentRequest assignmentRequest = AssignmentRequest.builder()
        .title("")
        .build();

        String assignmentString = objectMapper.writeValueAsString(assignmentRequest);

        doThrow(new InvalidAssignmentRequestException("Title can not be empty"))
                .when(assignmentService).CreateAssingment(assignmentRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/assignment")
        .contentType(MediaType.APPLICATION_JSON)
        .content(assignmentString))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllAssignments() throws Exception{
        List<AssignmentResponse> assignments = List.of(
            AssignmentResponse.builder().title("Title1").assignmentStatus(AssignmentStatus.ToDo).build(),
            AssignmentResponse.builder().title("Title2").assignmentStatus(AssignmentStatus.InProgress).build()
        );

        when(assignmentService.GetAllAssignments()).thenReturn(assignments);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/assignment")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2))
        .andExpect(jsonPath("$[0].title").value("Title1"))
        .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    public void testGetAllToDoAssignments() throws Exception{
        List<AssignmentResponse> assignments = List.of(
            AssignmentResponse.builder().title("Title1").assignmentStatus(AssignmentStatus.ToDo).build(),
            AssignmentResponse.builder().title("Title2").assignmentStatus(AssignmentStatus.ToDo).build()
        );

        when(assignmentService.GetAllToDoAssignments()).thenReturn(assignments);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/assignment/todo")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2))
        .andExpect(jsonPath("$[0].title").value("Title1"))
        .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    public void testGetAllInProgressAssignments() throws Exception{
        List<AssignmentResponse> assignments = List.of(
            AssignmentResponse.builder().title("Title1").assignmentStatus(AssignmentStatus.InProgress).build(),
            AssignmentResponse.builder().title("Title2").assignmentStatus(AssignmentStatus.InProgress).build()
        );

        when(assignmentService.GetAllInProgressAssignments()).thenReturn(assignments);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/assignment/inprogress")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2))
        .andExpect(jsonPath("$[0].title").value("Title1"))
        .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    public void testGetAllCompletedAssignments() throws Exception{
        List<AssignmentResponse> assignments = List.of(
            AssignmentResponse.builder().title("Title1").assignmentStatus(AssignmentStatus.Completed).build(),
            AssignmentResponse.builder().title("Title2").assignmentStatus(AssignmentStatus.Completed).build()
        );

        when(assignmentService.GetAllCompletedAssignments()).thenReturn(assignments);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/assignment/completed")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2))
        .andExpect(jsonPath("$[0].title").value("Title1"))
        .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    public void UpdateAssignment() throws Exception{
        String id = "27aaa552-a986-456f-af1c-2a09961e16d9";
        AssignmentResponse updatedAssignment = AssignmentResponse.builder()
        .assignmentStatus(AssignmentStatus.InProgress)
        .title("Title")
        .id(id)
        .build();

        when(assignmentService.UpdateAssignment(id)).thenReturn(updatedAssignment);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/assignment/{id}", id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.assignmentStatus").value("InProgress"));
    }
}
