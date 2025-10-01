package me.xaiterios.todo_list.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.xaiterios.todo_list.domain.Request.AssignmentRequest;
import me.xaiterios.todo_list.services.IAssignmentService;


@RestController
@RequestMapping("/api/assignment")
@RequiredArgsConstructor
public class AssignmentController {
    private final IAssignmentService assignmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateAssingment(@RequestBody AssignmentRequest assignmentRequest){
        assignmentService.CreateAssingment(assignmentRequest);
    }

    @GetMapping
    public ResponseEntity GetAllAssignments() {
        return new ResponseEntity<>(assignmentService.GetAllAssignments(), HttpStatus.OK);
    }

    @GetMapping("/todo")
    public ResponseEntity GetAllToDoAssignments() {
        return new ResponseEntity<>(assignmentService.GetAllToDoAssignments(), HttpStatus.OK);
    }

    @GetMapping("/inprogress")
    public ResponseEntity GetAllInProgressAssignments() {
        return new ResponseEntity<>(assignmentService.GetAllInProgressAssignments(), HttpStatus.OK);
    }
    
    @GetMapping("/completed")
    public ResponseEntity GetAllCompletedAssignments() {
        return new ResponseEntity<>(assignmentService.GetAllCompletedAssignments(), HttpStatus.OK);
    }
}