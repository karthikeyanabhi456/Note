import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';

import { SecretService } from '../../secret.service'; //use two double dot slash (../../) to come out from two folders and put slash to declare the file name
//import { ToastrService } from 'ngx-toastr';
//import { saveAs } from 'file-saver';
//private toastrService : ToastrService

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit
{
    
    constructor(private router : Router, private activatedRoute : ActivatedRoute, private secretService : SecretService)
    {
        
    }
    
    id : any;
    
    secret : any={};
    notes : any=[]; //
    note : any={}; //
    
    response : any={};

    ngOnInit()
    {
        console.log('New page');
        this.id = +this.activatedRoute.snapshot.paramMap.get('id');
        
        console.log(this.id);
        this.secretService.getOne(this.id).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                this.secret = this.response.content;
                this.notes = this.secret.notes;
            }
            else
                alert('error');
            });
    }
    
    save()
    {   
        console.log('save');
        this.notes.add(this.note);
        this.secret.notes = this.notes;
        
        console.log(this.secret);
        this.secretService.update(this.secret).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success', 'Saved');
                alert('Saved');
                this.router.navigate(['/list']);
            }
            else
                alert('error');
            });
        /*this.noteService.update(this.id, this.note).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                //this.toastrService.show('success', 'Saved');
                alert('Saved');
                this.router.navigate(['/list']);
            }
            else
                alert('error');
        });*/
    }
}