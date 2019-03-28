package com.teamup.project.exceptions;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(Exception.class)
	public void handleConflict(HttpServletResponse response, ApplicationException e) {
		e.printStackTrace();
		String errorMessage = e.getMessage();
		response.setStatus(500);
		response.setHeader("errorMessage", errorMessage);
	}

//	if (exception instanceof ApplicationException) {
//		ApplicationException e = (ApplicationException) exception;
//
//		int internalErrorCode = e.getErrorType().getInternalErrorCode();
//		String internalMessage = e.getMessage();
//		String externalMessage = e.getErrorType().getInternalMessage();
//		ErrorBean errorBean = new ErrorBean(internalErrorCode, internalMessage, externalMessage);
//		return Response.status(internalErrorCode).entity(errorBean).build();
//
//	} else if (exception instanceof Exception) {
//
//		String iternalMessage = exception.getMessage();
//		ErrorBean errorBean = new ErrorBean(601, iternalMessage,"General error");
//		return Response.status(601).entity(errorBean).build();
//	}
//
//	return Response.status(501).entity(null).build();
}


