import { Component, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from '../../_services/user.service';
import { Observable } from 'rxjs/Observable';
import { User } from '../../_models/user';
 
@Component({
    selector: 'app-delete-user',
    templateUrl: './delete-user.component.html',
    styleUrls: ['./delete-user.component.css'],
    providers: [UserService]
})
 
export class DeleteUserComponent {
 
    /*
        @Output will tell the parent component (AppComponent)
        that an event has happened (via .emit(), see readUsers() method below)
    */
    @Output() show_read_users_event = new EventEmitter();
 
    // @Input enable getting the user_id from parent component (AppComponent)
    @Input() user_id: any;
 
    // initialize user service
    constructor(private userService: UserService){}
 
    // user clicks 'yes' button
    deleteUser(){
        
        // delete data from database
        this.userService.deleteUser(this.user_id)
            .subscribe(
                 user => {
 
                    // show an alert to tell the user if user was created or not
                    console.log(user);
 
                    // go back to list of users
                    this.readUsers();
                 },
                 error => console.log(error)
             );
    }
 
    // user clicks the 'read users' button
    readUsers(){
        this.show_read_users_event.emit({ title: "Read Users" });
    }
}