import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SigninComponent } from './signin/signin.component';
import { AccountCreateComponent } from './account-create/account-create.component'; //to get in to the folder use dot slash (./) to come out of the file, type folder name and use slash to type file name (./folder name/file name)
import { WelcomeComponent } from './welcome/welcome.component';
import { ListComponent } from './note/list/list.component';
import { AddComponent } from './note/add/add.component';
import { ViewComponent } from './note/view/view.component';
import { InfoComponent } from './info/info.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { TestComponent } from './test/test.component';


const routes: Routes =
[
    { path : 'signin', component : SigninComponent },
    { path : 'create-account', component : AccountCreateComponent  },
    { path : 'welcome/:id', component : WelcomeComponent },
    { path : 'list/:id', component : ListComponent },
    { path : 'add/:id', component : AddComponent },
    { path : 'view/:id', component : ViewComponent },
    { path : 'info', component : InfoComponent },
    { path : 'feedback', component : FeedbackComponent },
    { path : 'test', component : TestComponent },
    { path : '', redirectTo: 'test', pathMatch: 'full' } //for testing purposes
    //{ path : '', redirectTo: 'signin', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
export const RoutingComponents = [TestComponent, SigninComponent, AccountCreateComponent, WelcomeComponent, ListComponent, AddComponent, ViewComponent, InfoComponent, FeedbackComponent];