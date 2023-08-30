import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions =
{
        'responseType' : 'blob' as 'json'
};


@Injectable()
export class NoteService
{
    constructor(private http : HttpClient)
    {
        
    }
    
    private url = '/api/secret/note';
    
    public getOne(id)
    {
        return this.http.get<any>(this.url+'/get/'+id);
    }
    
    /*public update(id, note) //(id : any, note : any) can also be used
    {
        return this.http.post<any>(this.url+'/update/'+id, note);
    }*/
    
    /*public delete(id)
    {
        return this.http.delete<any>(this.url+'/delete/'+id);
    }*/
    
    public pagination(id, paginationRequest)
    {
        return this.http.post<any>(this.url+'/pagination/'+id, paginationRequest);
    }
}