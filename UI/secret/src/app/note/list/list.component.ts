import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';

import { SecretService } from '../../secret.service'; //use two double dot slash (../../) to come out from two folders and put slash to declare the file name
import { NoteService } from '../note.service';
import { MasterService } from '../../master.service';
//import { ToastrService } from 'ngx-toastr';
//import { saveAs } from 'file-saver';
//private toastrService : ToastrService

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit
{
    
    constructor(private router : Router, private activatedRoute : ActivatedRoute, private noteService : NoteService, private secretService : SecretService, private masterService : MasterService)
    {
        
    }
    
    id : any;
    
    noteList : any=[]; //list is used for readability
    
    sizeList : any=[] = [5, 10, 15, 20]; //number[] can be used instead of any=[]
    pageList : any=[];
    paginationRequest : any={};
    paginationResponse : any={};
    secret : any={};
    response : any={};

    ngOnInit()
    {
        console.log('New page');
        this.id = +this.activatedRoute.snapshot.paramMap.get('id');
        this.masterService.constructPagination(this.paginationRequest);
        this.getList();
    }
    
    pageIndex(index)
    {
        return ((this.paginationRequest.page*this.paginationRequest.size)+(index+1));
    }
    
    getList()
    {
        console.log('getList');
        
        console.log(this.id);
        console.log(this.paginationRequest);
        this.noteService.pagination(this.id, this.paginationRequest).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
            {
                this.paginationResponse = this.response.content;
                this.noteList = this.paginationResponse.content;
                if(this.noteList == null)
                    this.noteList = []; //empty array has to be declared only then items can be pushed
                for(let i=1;i<=this.paginationResponse.totalPages;i++)
                {
                    this.pageList.push(i);
                }
            }
            else
                alert('error');
            });
    }
    
    /*keyStroke(event)
    {
       //event.target is used to detect the key stroked
       this.paginationRequest.searchKey = event.target.value; //used to return the value in the field
       this.getList();
    }*/
    
    applyFilter(filterValue)
    {
       this.paginationRequest.searchKey = filterValue;
       this.getList();
    }
    
    sort()
    {
        if(this.paginationRequest.direction == 'ASC')
            this.paginationRequest.direction = 'DESC';
        else
            this.paginationRequest.direction = 'ASC';
        
        this.getList();
        return;
    }
    
    create()
    {
        console.log('create');
        this.router.navigate(['/add', this.id]);
    }
    
    view(id)
    {
        console.log('view');
        console.log(id); //from internal ui input
        this.router.navigate(['/view', id])
    }
    
    delete(id)
    {
        console.log('delete');
        
        console.log(id);
        this.secretService.update(this.secret).subscribe( data => {
            console.log(data);
            this.response = data;
            if(this.response.code == 200)
                //this.toastrService.show('success', 'Saved successfully');
               alert('Deleted successfully');
            else
                alert('error');
            });
            this.getList();
    }
}
/*var body =
{
    ...this.secretDto,
    feedbackDto : 
        {
            id : this.secretDto.feedbackDto.id,
            msg : this.msg
        }
    noteDtos :
        [
            {
                "msg" : this.msg
            }
        ]
};*/