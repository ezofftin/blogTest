package kr.ezen.blogTest.exception;

import kr.ezen.blogTest.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController // @Controller + @ResponseBody
public class BlogExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    public String globalExceptionHandler(Exception e) {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    @ExceptionHandler(value = Exception.class)
    public ResponseDTO<String> globalExceptionHandler(Exception e) {
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
