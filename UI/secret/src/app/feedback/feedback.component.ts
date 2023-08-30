import { Component, OnInit } from '@angular/core';

import { FeedbackService } from './feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit
{
    response : any={};

    resultSetList : any=[];

  constructor(private feedbackService : FeedbackService)
  {
      
  }

  ngOnInit()
  {
      console.log('New page');
      
      this.feedbackService.feedbackUn().subscribe( data => {
          console.log(data);
          this.response = data;
          if(this.response.code == 200)
              this.resultSetList = this.response.content;
          else
              alert('error');
          });
  }
}
