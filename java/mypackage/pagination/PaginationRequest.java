package mypackage.pagination;

import lombok.Data;

@Data
public class PaginationRequest
{
	int page;
	
	int size;
	
	String direction;
	
	String properties;
	
	String searchKey;
}