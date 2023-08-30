import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions =
{
        'responseType' : 'blob' as 'json'
};

@Injectable()
export class FeedbackService
{
    constructor(private http : HttpClient)
    {
        
    }
    
    private url = '/api/secret/feedback';
    
    public feedbackUn()
    {
        return this.http.get<any>(this.url+'/unsfeedback');
    }
}