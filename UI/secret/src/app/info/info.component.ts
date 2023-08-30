import { Component, OnInit } from '@angular/core';

import { InfoService } from './info.service';
//import { ToastrService } from 'ngx-toastr';
//import { saveAs } from 'file-saver';
//private toastrService : ToastrService

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit
{
    
    constructor(private infoService : InfoService)
    {
        
    }
    
    id : any;
    
    city : any={};
    course : any={};
    role : any={};
    
    cityList : any=[];
    idList : any=[];
    courseList : any=[];
    roleList : any=[];
    
    response : any={};

    ngOnInit()
    {
        console.log('New page');
        this.getCityList(); //list objects are suffixed with List for readability
        this.getCourseList();
        this.getRoleList(); //asynchronous group loading //loads multiple promises
    }
    
    //City
    getCityList()
    {
        console.log('getCityList');
        
        this.infoService.getAllCityList().subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                this.cityList = this.response.content;
                if(this.cityList == null)
                this.cityList = [];
             }
             else
                 alert('error');
             });
    }
    
    addCity()
    {
        console.log('addCity');
        console.log(this.city);
        this.cityList.push(this.city);
        console.log(this.cityList); //
        //this.city.name = null;
    }
    
    createCityList()
    {
        console.log('createCityList');
        
        console.log(this.cityList);
        this.infoService.updateCityList(this.cityList).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
              //this.toastrService.show('success', 'Saved');
                alert('Saved');
                this.getCityList(); //ngOnInit() should not be called as it should be called only once
            }
            else
                alert('error');
        });
    }
    
    deleteCity(index, id) //index name should be used for readability //index should be first used for readability
    {
        console.log('deleteCity');
        console.log(index);
        console.log(id);
        this.cityList.splice(index, 1); //1 check
        this.idList.push(id);
    }
    
    deleteCityList()
    {
        console.log('deleteCityList');
        
        console.log(this.idList);
        this.infoService.deleteCityList(this.idList).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success', 'Saved');
                alert('Deleted successfully');
                this.getCityList(); //ngOnInit() should not be called as it should be called only once
            }
            else
                alert('error');
        });
    }
    
    //Course
    getCourseList()
    {
        console.log('getCourseList');
        
        this.infoService.getAllCourseList().subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                this.courseList = this.response.content;
            else
                alert('error');
            });
    }
    
    createCourse()
    {
        console.log('createCourse');
        
        console.log(this.course);
        this.infoService.updateCourse(this.course).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success', 'Saved');
                alert('Saved');
                this.getCourseList();
            }
            else
                alert('error');
        });
    }
    
    deleteCourse(id) //(id : number) or (id : any) can also be used
    {
        console.log('deleteCourse');
        
        console.log(id);
        this.infoService.deleteCourse(id).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success', 'Saved');
                alert('Deleted successfully');
                this.getCourseList();
            }
            else
                alert('error');
        });
    }
    
    //Role
    getRoleList()
    {
        console.log('getCourseList');
        
        this.infoService.getAllRoleList().subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                this.roleList = this.response.content;
            else
                alert('error');
            });
    }
    
    createRole()
    {
        console.log('createRole');
        
        console.log(this.role);
        this.infoService.updateRole(this.role).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success', 'Saved');
                alert('Saved');
                this.getRoleList();
            }
            else
                alert('error');
        });
    }
    
    deleteRole(id)
    {
        console.log('deleteRole');
        
        console.log(id);
        this.infoService.deleteRole(id).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success', 'Saved');
                alert('Deleted successfully');
                this.getRoleList();
            }
            else
                alert('error');
        });
    }
}