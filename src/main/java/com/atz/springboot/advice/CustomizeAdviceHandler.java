package com.atz.springboot.advice;

import com.atz.springboot.controller.IndexController;
import com.atz.springboot.exception.CustomizeException;
import com.atz.springboot.enums.CustomizeErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class CustomizeAdviceHandler {



}