//package com.soop.pages.config;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import java.nio.file.AccessDeniedException;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handle404(NoHandlerFoundException ex, Model model) {
//        model.addAttribute("error", "404");
//        model.addAttribute("message", "Page not found");
//        return "404";
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public String handle403(AccessDeniedException ex, Model model) {
//        model.addAttribute("error", "403");
//        model.addAttribute("message", "Access denied");
//        return "403";
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handle500(Exception ex, Model model) {
//        model.addAttribute("error", "500");
//        model.addAttribute("message", "Internal server error");
//        return "500";
//    }
//}
