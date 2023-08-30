package mypackage.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

import mypackage.response.HttpStatusCode;

@Data
//@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "My Custom Error") //if @ExceptionHandler and exception handling using try/catch is not present @ResponseStatus will be invoked //reason will be displayed as nothing but a message //(value =) syntax is not needed if only HttpStatus has to be used  //if (reason =) is used (value =) should be used //third priority for an exception
public class BadRequestException extends RuntimeException //ExceptionResponse //user-defined exception for validations //RuntimeException is always needed for this class to work
{
	private static final long serialVersionUID = 1L;

	//HttpStatusCode httpStatusCode;
	
	Integer code;
	
	String msg; //message //description
	
	//Date timestamp; //optional
	
	public BadRequestException(HttpStatusCode httpStatusCode)
	{
		//super(httpStatusCode); //super() works other than enum type objects
		//this.httpStatusCode = httpStatusCode;
		this.code = httpStatusCode.code;
		this.msg = httpStatusCode.msg;
		//this.timestamp = new Date();
	}
}