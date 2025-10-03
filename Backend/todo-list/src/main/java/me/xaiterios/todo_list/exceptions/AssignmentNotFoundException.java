package me.xaiterios.todo_list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AssignmentNotFoundException extends RuntimeException{
    public AssignmentNotFoundException(String message){
        super(message);
    }
}
