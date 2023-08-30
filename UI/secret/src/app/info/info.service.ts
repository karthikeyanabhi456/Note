import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions =
{
        'responseType' : 'blob' as 'json'
};

@Injectable()
export class InfoService //unlike server-side the client-side structure can be of any type
{
    constructor(private http : HttpClient)
    {
        
    }
    
    private url = '/api/secret';
    
    //City
    public getAllCityList()
    {
        return this.http.get<any>(this.url+'/city/get/all');
    }
    
    public updateCityList(cities)
    {
        return this.http.put<any>(this.url+'/city/updates', cities);
    }
    
    public deleteCityList(ids)
    {
        return this.http.delete<any>(this.url+'/city/deletes', ids);
    }
    
    //Course
    public getAllCourseList()
    {
        return this.http.get<any>(this.url+'/course/get/all');
    }
    
    public updateCourse(course)
    {
        return this.http.put<any>(this.url+'/course/update', course);
    }
    
    public deleteCourse(id)
    {
        return this.http.delete<any>(this.url+'/course/delete/'+id);
    }
    
    //Role
    public getAllRoleList()
    {
        return this.http.get<any>(this.url+'/role/get/all');
    }
    
    public updateRole(role)
    {
        return this.http.put<any>(this.url+'/role/update', role);
    }
    
    public deleteRole(id)
    {
        return this.http.delete<any>(this.url+'/role/delete/'+id);
    }
}