package me.xaiterios.todo_list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidAssignmentRequestException extends RuntimeException{
    public InvalidAssignmentRequestException(String message){
        super(message);
    }
}
