package me.xaiterios.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.xaiterios.todo_list.domain.Assignment;
import me.xaiterios.todo_list.domain.AssignmentStatus;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, String>{
    public List<Assignment> findAllByAssignmentStatus(AssignmentStatus assignmentStatus);
}