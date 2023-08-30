package mypackage.response;

import lombok.Getter;

@Getter //@Setter is not used in HttpStatusCode enum but it can be used
public enum HttpStatusCode //user-defined
{
	//standard
	SUCCESS(200,"SUCCESS"),
	INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR"),
	
	//custom
	NULL(0, "NULL"),
	INVALID(1001,"INVALID"),
	LIMIT(1, "LIMIT"),
	PATTERN(2, "PATTERN"),
	USER(3, "USER"),
	CANNOT_UPDATE_LOCKED_RECORD(4, "Two users using simultaneously"),
	CANNOT_UPDATE_DELETED_RECORD(5, "Record is not there to delete");
	
	public Integer code;
	public String msg; //message //description
	
	private HttpStatusCode(Integer code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}
}