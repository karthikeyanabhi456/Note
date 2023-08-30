import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { FormControl } from '@angular/forms';

@Component( {
    selector: 'app-test',
    templateUrl: './test.component.html',
    styleUrls: ['./test.component.css']
} )
export class TestComponent {
    myControl = new FormControl();

    options: String[] = ['Mary'];

    constructor(private router: Router)
    {

    }

    ngOnInit()
    {
        console.log('New page');
    }

    /*displayFn( option?: any ): String | undefined {
        return option ? option.name : undefined;
    }*/
}
