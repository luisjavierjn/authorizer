import { Component, ViewEncapsulation } from '@angular/core';
import { User } from '../_models/index';

@Component({
    selector: 'app-menu',
    templateUrl: 'menu.component.html',
    styleUrls: [
        './menu.component.css'
    ],
    encapsulation: ViewEncapsulation.None
})

export class MenuComponent {
    currentUser: User;

    constructor() {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
}