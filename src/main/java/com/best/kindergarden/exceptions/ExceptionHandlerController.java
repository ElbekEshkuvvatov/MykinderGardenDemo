package com.best.kindergarden.exceptions;
import com.best.kindergarden.model.dto.CustomErrors;
import com.best.kindergarden.model.dto.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    //Bu metod validation da kelgan exceptions ni tutish uchun
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<CustomErrors> errors = new LinkedList<>();
          CustomErrors customErrors = new CustomErrors();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            customErrors.setField(error.getField());
            customErrors.setMessage(error.getDefaultMessage());
            errors.add(customErrors);

        }

        /*
           errors: [{
           "field": "username",
           "message": "username required must be not empty!"
           },
           {
           "field": "password",
           "message": "password required"
           }]
         */
        Response<List<CustomErrors>> response = new Response<>("not valid", errors, status.value());
        return new ResponseEntity<>(response, status);
    }

    //This method for my exception Handle (ResourceNotfoundException)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> ResourceNotFoundException(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.ok(new Response<>(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }


}
