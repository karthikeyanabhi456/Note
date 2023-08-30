import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router'; //for navigation only
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { SecretService } from '../secret.service';
//import { ToastrService } from 'ngx-toastr';
//toastrService : ToastrService

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent //implements OnInit //component.spec.ts is not needed for all components except app.component.spec.ts
{
    id : any
    
    formGroup : FormGroup;
    
    secret : any={}; //private by default //private can also be used

    response : any={}; //any, any={}, any=[] are data types //there should be no spaces in any={} and any=[]
    //any={} is equivalent to any object and any=[] is equivalent to any list

    preUser : any; //any can be used instead of boolean, number and string 
    //any is equivalent to both any={} and any=[]
    //even though any can be used any={} for object and any=[] for list is used for readability
    
    constructor(private router : Router, private formBuilder : FormBuilder, private secretService : SecretService) //constructor() is first only then ngOnInit()
    {
        this.formGroup = this.formBuilder.group({
            'unForm' : [null, Validators.required],
            'pwdForm' : [null, Validators.required]
        }) //client-side validation
    }
    
    ngOnInit() //void data type
    {
        console.log('New page');
        if(localStorage.getItem('user') != null) //'' is used instead of "" which also works
            this.preUser = localStorage.getItem('user');
        else
            this.preUser = '';
    }
    
    signin() //returns void by default //signIn() : void expression is also void
    {
        console.log('signin'); //internal ui input
        this.secret.un = this.formGroup.value.unForm;
        this.secret.pwd = this.formGroup.value.pwdForm;
        
        console.log(this.secret);
        this.secretService.signIn(this.secret).subscribe( data => { 
            console.log(data); //it is good to see the arriving and departing data 
            this.response = data; //in promise not asynchronous
            if(this.response.code == 200)
            {
                this.secret = this.response.content;
                this.id = this.secret.id; //this.id is recommended as it can be used anywhere
                //let paramId = this.secret.id;
                localStorage.setItem('user', this.secret.un);
                //this.toastrService.show('success','Logged in');
                this.router.navigate(['/welcome', this.id]);
            }
            else if(this.response.code == 2 || this.response.code == 3 || this.response.code == 4)
                alert('Invalid');
                //this.toastrService.show('error', 'Invalid');
            else
                alert('error');
        }/*, alert =>
        {
            console.log('error') //optional
        }*/);
    }
    
    info()
    {
        console.log('info');
        this.router.navigate(['/info']);
    }
    
    refresh()
    {
        console.log('refresh');
        this.router.navigateByUrl(this.router.url).then( () => window.location.reload() ); //this.router.url gives current url 
    }
    
    restart()
    {
        console.log('restart');
        this.secret.un = this.formGroup.value.unForm;
        this.secret.pwd = this.formGroup.value.pwdForm;
        
        console.log(this.secret);
        this.secretService.restart(this.secret).subscribe( data => { 
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success','Restarted');
                alert('Restarted');
            }
            else
                alert('error');
        });
    }
}
