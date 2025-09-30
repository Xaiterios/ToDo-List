package me.xaiterios.todo_list.domain.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xaiterios.todo_list.domain.AssignmentStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponse {
    private String id;
    private String title;
    private AssignmentStatus assignmentStatus;
}
