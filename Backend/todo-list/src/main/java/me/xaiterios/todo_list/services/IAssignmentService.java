package me.xaiterios.todo_list.services;

import java.util.List;

import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
import me.xaiterios.todo_list.domain.Response.AssignmentResponse;

public interface IAssignmentService {
    public void CreateAssingment(AssignmentRequest assignmentRequest);
    public List<AssignmentResponse> GetAllAssignments();
    public List<AssignmentResponse> GetAllToDoAssignments();
    public List<AssignmentResponse> GetAllInProgressAssignments();
    public List<AssignmentResponse> GetAllCompletedAssignments();
    public AssignmentResponse UpdateAssignment(String id);
}
