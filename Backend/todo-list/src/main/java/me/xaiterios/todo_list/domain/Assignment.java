package me.xaiterios.todo_list.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assignments")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    private String id;
    private String title;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus assignmentStatus;
}
