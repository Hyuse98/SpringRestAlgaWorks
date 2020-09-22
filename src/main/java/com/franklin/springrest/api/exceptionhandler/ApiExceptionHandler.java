package com.franklin.springrest.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.franklin.springrest.domain.exception.ExceptionsBO;



@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ExceptionsBO.class)
	public ResponseEntity<Object> handlerBO(ExceptionsBO ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var problema = new ProblemasTratados();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
		
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		var campos = new ArrayList<ProblemasTratados.Campo>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String n = ((FieldError) error).getField();
			String m = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new ProblemasTratados.Campo(n, m));
		}
		
		var problema = new ProblemasTratados();
		problema.setStatus(status.value());
		problema.setTitulo("Campos Invalidos, Preencha Corretamente");
		problema.setDataHora(OffsetDateTime.now());
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

}
