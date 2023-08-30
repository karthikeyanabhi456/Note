package mypackage.pagination;

import java.util.List;

import lombok.Data;

@Data
public class PaginationResponse
{
	Integer numberOfElements;
	
	Integer totalPages;
	
	Long totalElements;
	
	List<Object> content; //as of Pageable class
}