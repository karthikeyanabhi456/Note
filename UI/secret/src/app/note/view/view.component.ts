import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';

import { NoteService } from '../note.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit
{
    
    constructor(private activatedRoute : ActivatedRoute, private noteService : NoteService)
    {
        
    }
    
    id : number;
    
    note : any={};
    
    response : any;

    ngOnInit()
    {
        console.log('New page');
        this.id = +this.activatedRoute.snapshot.paramMap.get('id');
        
        console.log(this.id);
        this.noteService.getOne(this.id).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                this.note = this.response.content;
            else
                alert('error');
            });
    }
}