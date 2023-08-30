import { BrowserModule } from '@angular/platform-browser';
//import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
//CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
//import { MatFormFieldModule } from '@angular/material/form-field';
//import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'; //one dot (.) is used to come out one step out of the file and put slash (/) to declare the file name
import { RoutingComponents } from './app-routing.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
//import { AngularEditorModule } from '@kolkov/angular-editor';
//import { ToastrModule } from 'ngx-toastr';

import { SecretService } from './secret.service';
import { NoteService } from './note/note.service';
import { InfoService } from './info/info.service';
import { FeedbackService } from './feedback/feedback.service';
import { MasterService } from './master.service';
import { FeedbackComponent } from './feedback/feedback.component';
import { ListComponent } from './note/list/list.component';
import { AddComponent } from './note/add/add.component';
import { ViewComponent } from './note/view/view.component';

@NgModule({
declarations: [
  AppComponent,
  RoutingComponents,
  ListComponent,
  AddComponent,
  ViewComponent,
  FeedbackComponent //app and routing components and additional components are to be declared here
],
imports: [
  BrowserModule,
  //BrowserAnimationsModule,
  //MatFormFieldModule,
  //MatInputModule,
  //CommonModule,
  AppRoutingModule,
  FormsModule,
  ReactiveFormsModule,
  HttpClientModule,
  //AngularEditorModule,
  //ToastrModule.forRoot(); //Additional modules imported via net are to be added in imports
],
/*exports: [
          BrowserModule,
          BrowserAnimationsModule,
          MatFormFieldModule,
          CommonModule,
          AppRoutingModule,
          FormsModule,
          ReactiveFormsModule,
          HttpClientModule,
          //AngularEditorModule,
          //ToastrModule.forRoot(); //Additional modules imported via net are to be added in imports
        ],*/
providers: [SecretService, NoteService, InfoService, FeedbackService, MasterService], //services are to be declared here
bootstrap: [AppComponent]
/*schemas:
    [ CUSTOM_ELEMENTS_SCHEMA,
     ]*/
})
export class AppModule { } //client //Additional modules are imported here //modules combine here to run