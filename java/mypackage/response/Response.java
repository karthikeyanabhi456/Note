package mypackage.response;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response
{
	Integer code;
	
	String msg; //message //description
	
	//Date timestamp;
	
	Object content;
	
	public Response(HttpStatusCode httpStatusCode)
	{
		this.code = httpStatusCode.code;
		this.msg = httpStatusCode.msg;
	}
	
	public void setMsg(HttpStatusCode httpStatusCode)
	{
		this.code = httpStatusCode.code;
		this.msg = httpStatusCode.msg;
	}
	
	public void setMsg(BadRequestException bre)
	{
		this.code = bre.code;
		this.msg = bre.msg;
		//this.timestamp = bre.timestamp;
	}
	
	public void setMsg(HttpStatusCode httpStatusCode, Object content)
	{
		this.code = httpStatusCode.code;
		this.msg = httpStatusCode.msg;
		this.content = content;
	}
}