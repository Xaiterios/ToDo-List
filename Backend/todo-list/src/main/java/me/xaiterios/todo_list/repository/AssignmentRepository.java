package me.xaiterios.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.xaiterios.todo_list.domain.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, String>{

}
