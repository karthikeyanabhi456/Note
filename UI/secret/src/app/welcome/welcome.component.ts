import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router'; //ActivatedRoute can be used to get both paramId and navigation as it contains navigate object
//import { ToastrService } from 'ngx-toastr';
//private toastrService : ToastrService
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { SecretService } from '../secret.service'; //single dot (.) is used to come out of the file and another single dot (.) is used to come out of the folder and put slash (/) to declare the file name. i.e. use double dot slash (../) to come out from a folder
//import { FeedbackDto } from '../FeedbackDto';
import { InfoService } from '../info/info.service';
import { MasterService } from '../master.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit
{

    id : any;

    secret : any={};
    //feedbackDto : FeedbackDto;
    feedback : any={}; //recommended instead of using class

    //msg : any;

    feedbackMsg : any; //binding variable //if the member of the class is not defined the name of the member has to be used with its class name first following its name for readability

    /*courseList : any=[];
    userCourseList : any=[];*/

    response : any={};
  
    constructor(private router : Router, private formBuilder : FormBuilder, private activatedRoute : ActivatedRoute, private secretService : SecretService, private infoService : InfoService, public masterService : MasterService)
    {
	  
    }
  
    ngOnInit()
    {
        console.log('New page');
        //let id = parseInt(this.activatedRoute.snapshot.paramMap.get('id'));
        //let id = this.activatedRoute.snapshot.params['id'];
        this.id = +this.activatedRoute.snapshot.paramMap.get('id'); //get('id') is syntax
        
        console.log(this.id);
        this.secretService.getOne(this.id).subscribe( data => {
	            console.log(data);
	            this.response = data;
	            if(this.response.code == 200)
	            {
	                this.secret = this.response.content;
	                this.feedback = this.secret.feedback; //unpacking
	                if(this.feedback != null) //likely true conditions are placed in if statement and less likely to else statement
	                    this.feedbackMsg = this.feedback.msg;
	                else
	                {
	                    this.feedback = {};
	                    this.feedbackMsg = ''; //own objects cannot be created so binding variable is used
	                    /*if(this.secret.courses != null)
	                        //this.userCourseList = [];//this.secret.courses;
	                    else
	                        this.userCourseList = [];*/   
	                }
	            }
	            else
	                alert('error');
	            });
	    /*this.infoService.getAllCourseList().subscribe( data => { //page loading //synchronous lazy loading //loads promise after promise
                console.log(data);
                this.response = data;
                if(this.response.code == 200)
                    this.courseList = this.response.content;
                else
                    alert('error');
	            });*/
    }
    
    goToSecret()
    {
        console.log('goToSecret');
        this.router.navigate(['/list', this.id]);
    }
    
    /*addCourse(index, course)
    {
        console.log('addCourse');
        console.log(index)
        console.log(course);
        this.userCourseList.add(course);
        this.courseList.splice(index, 1);
    }
    
    removeCourse(index, course)
    {
        console.log('removeCourse');
        console.log(index)
        console.log(course);
        this.courseList.add(course);
        this.userCourseList.splice(index, 1);
    }*/
    
    downloadExcel()
    {
        console.log('downloadExcel');
        
        console.log(this.id);
        /*this.secretService.downloadExcel(this.id).subscribe( (downloaddata : ArrayBuffer) => {
            var file = new Blob([downloaddata],
            {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' 
            });
            saveAs(file,"Secret"+".xlsx");
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                alert('Downloaded successfully');
                //this.toastrService.show('success','Downloaded successfully');
            else
                alert('error');
            });*/
    }
    
    downloadPdf()
    {
        console.log('downloadPdf');
        
        console.log(this.id);
        /*this.secretService.downloadPdf(this.id).subscribe( (downloaddata : ArrayBuffer) => {
            var file = new Blob([downloaddata],
            {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' 
            });
            saveAs(file,"Secret"+".xlsx");
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                alert('Downloaded successfully');
                //this.toastrService.show('success','Downloaded successfully');
            else
                alert('error');
            });*/
    }
  
    submit()
    {
        console.log('submit');
        console.log(this.feedbackMsg);
        this.feedback.msg = this.feedbackMsg;
        this.secret.feedback = this.feedback;
        //this.secret.courses = this.userCourseList;
        console.log(this.secret);
        this.secretService.update(this.secret).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                //this.toastrService.show('success', 'Thank You');
                alert('Thank You');
            else
                alert('error');
            });
    }
    
    delAcc() //deleteAccount //keep minimum variable or method name length
    {
            console.log('delAcc');
            
            console.log(this.id);
            this.secretService.delete(this.id).subscribe( data => {
                console.log(data);
                this.response = data;
                if(this.response.code == 200)
                {
                    //this.toastrService.show('success', 'Account deleted successfully');
                    alert('Account deleted successfully');
                    this.router.navigate(['/signin']);
                }
                else
                    alert('error');
                });
    }
}