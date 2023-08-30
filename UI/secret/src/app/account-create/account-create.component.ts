import { Component, OnInit } from '@angular/core';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
//import { ToastrService } from 'ngx-toastr';
//private toastrService : ToastrService

import { SecretService } from '../secret.service';

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit
{
    formGroup : FormGroup;

    secret : any={};

    response : any={};
    //new() is used because it is newly created from client-side by instantiating using new() to go for/to the server-side
    //new is not used because the object from the server-side directly binds to the property
    
    constructor(private formBuilder : FormBuilder, private secretService : SecretService)
    {
        this.formGroup = this.formBuilder.group({
            'unForm' : [null, Validators.required],
            'firstNameForm' : [null],//, Validators.required],
            'lastNameForm' : [null],//, Validators.required],
            'genderForm' : [null],
            'DobForm' : [null],
            'mobileForm' : [null],
            'pwdForm' : [null, Validators.required],
        })
    }
    
    ngOnInit()
    {
        console.log('New page');
    }
    
    create()
    {
        console.log('create');
        this.secret.un = this.formGroup.value.unForm;
        this.secret.firstName = this.formGroup.value.firstNameForm;
        this.secret.lastName = this.formGroup.value.lastNameForm;
        this.secret.gender = this.formGroup.value.genderForm;
        this.secret.dob = this.formGroup.value.DobForm;
        this.secret.mobile = this.formGroup.value.mobileForm;
        this.secret.pwd = this.formGroup.value.pwdForm;
        
        console.log(this.secret);
        this.secretService.update(this.secret).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                //this.toastrService.show('success', 'Account Created'); 
                alert('Account created');
            else if(this.response.code == 0 || this.response.code == 2)
                //this.toastrService.show('error', 'Invalid');
                alert('Invalid');
            else if(this.response.code == 3)
                //this.toastrService.show('error', 'User name taken');
                alert('User name taken');
            else
                alert('error');
            });
    }
}