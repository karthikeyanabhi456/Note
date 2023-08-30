package mypackage.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;

public class ValidateUtil
{
	public static void throwExcIfNull(Object object, HttpStatusCode httpStatusCode)
	{
		if(object == null || ObjectUtils.isEmpty(object))
			throw new BadRequestException(httpStatusCode); //Validation class throws only user-defined BadRequestException
	}
	
	public static void throwExcIfObj(Object object, HttpStatusCode httpStatusCode)
	{
		if(object != null || !ObjectUtils.isEmpty(object))
			throw new BadRequestException(httpStatusCode);
	}
	
	public static void checkLimit(Object object, int min, int max, HttpStatusCode httpStatusCode)
	{
		//minimum length check
		if(!(object.toString().length() >= min)) //toString() converts object to a string //string should be greater than or equal to the minimum length
			throw new BadRequestException(httpStatusCode);
		//maximum length check
		if(!(object.toString().length() <= max)) //string should be lesser than or equal to the maximum length
			throw new BadRequestException(httpStatusCode);
	}
	
	public static void checkPattern(String input, String regex, HttpStatusCode httpStatusCode)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean b = matcher.matches();
		if(b == false)
			throw new BadRequestException(httpStatusCode);
	}
}