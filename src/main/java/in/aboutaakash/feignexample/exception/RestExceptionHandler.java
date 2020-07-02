package in.aboutaakash.feignexample.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
import in.aboutaakash.feignexample.dto.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(Exception ex)
    {
        return new ErrorResponse(400, "Bad Request");
    }
    
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse unKnownException(Exception ex)
    {
        return new ErrorResponse(404, "Post Not Found");
    }
    
    @ExceptionHandler(value = { FeignException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse feignException(Exception ex)
    {
        return new ErrorResponse(500, "May be, the other microservice is down.");
    }
    
    /* In this way we can define all the possible exceptions and will be able to handle all the exceptions. */
}