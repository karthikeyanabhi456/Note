import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

//const httpOptions = { headers : new HttpHeaders({'ContentType' : 'application/json'}) } //only for data

const httpOptions =
{
        'responseType' : 'blob' as 'json'
}; //for file downloads

@Injectable()
export class MasterService //application commons are declared here
{
    /*constructor(private http : HttpClient) //constructor method is recommended to declare in this way instead of constructor() {} as it looks like a calling method and for readability
    {
        
    }*/
    
    public date()
    {
        return "dd-MM-yyyy";
    }
    
    //currency
    
    //
    
    constructPagination(paginationRequest)
    {
        console.log('constructPagination');
        /*this.paginationRequest.page = 0;
        this.paginationRequest.size = 5;
        this.paginationRequest.properties = 'msg'; //sort field
        this.paginationRequest.direction = 'ASC';
        this.paginationRequest.searchKey = '';*/
        paginationRequest.page = 0;
        paginationRequest.size = 5;
        paginationRequest.properties = 'msg'; //sort field
        paginationRequest.direction = 'ASC';
        paginationRequest.searchKey = '';
        return paginationRequest;
    }
    
    /*pageIndex(index)
    {
        return ((this.paginationRequest.page*this.paginationRequest.size)+(index+1));
    }*/
    
    displayFn( option?: any ): String | undefined {
      return option ? option.name : undefined;
    }
}