import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

//const httpOptions = { headers : new HttpHeaders({'ContentType' : 'application/json'}) } //only for data

const httpOptions =
{
        'responseType' : 'blob' as 'json'
}; //for file downloads

@Injectable()
export class SecretService
{
    constructor(private http : HttpClient) //constructor method is recommended to declare in this way instead of constructor() {} as it looks like a calling method and for readability
    {
        
    }
    
    private url = '/api/secret';
    
    public getOne(id) //parameter id full name is not needed as it is defined by its class or calling method
    {
        return this.http.get<any>(this.url+'/get/'+id); //<> is bind property i.e.assignable type
    }
    
    /*public getAll()
    {
        return this.http.get<any>(this.url+'/get/all');
    }*/
    
    /*public save(secretDTO)
    {
        return this.http.post<any>(this.url+'/save', secretDTO);
    }*/
    
    public update(secret)
    {
        return this.http.put<any>(this.url+'/update', secret);
    }
    
    public delete(id)
    {
        return this.http.delete<any>(this.url+'/delete/'+id);
    }
    
    public signIn(secret)
    {
        return this.http.post<any>(this.url+'/signin',secret);
    }
    
    /*public downloadExcel(id)
    {
        return this.http.get<any>(this.url+'/download/excel/'+id, httpOptions);
    }*/
    
    /*public uploadExcel(id, secret)
    {
        return this.http.post<any>(this.url+'/upload/excel/'+id, httpOptions);
    }*/
    
    public restart(secret)
    {
        return this.http.post<any>(this.url+'/restart', secret);
    }
}