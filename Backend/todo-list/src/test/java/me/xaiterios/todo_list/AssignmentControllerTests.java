package me.xaiterios.todo_list;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.xaiterios.todo_list.controller.AssignmentController;
import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
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
}
