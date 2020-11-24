import { Component, OnInit, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { UserService } from '../../_services/user.service';
import { RoleService } from '../../_services/role.service';
import { Observable } from 'rxjs/Observable';
import { User } from '../../_models/user';
import { Role } from '../../_models/role';

import { AlertService } from '../../_services/alert.service';
 
@Component({
    selector: 'app-read-one-user',
    templateUrl: './read-one-user.component.html',
    styleUrls: ['./read-one-user.component.css'],
    providers: [UserService]
})
 
export class ReadOneUserComponent implements OnInit {
 
    /*
        @Output will tell the parent component (AppComponent)
        that an event has happened (via .emit(), see readUsers() method below)
    */
    @Output() show_read_users_event = new EventEmitter();
 
    // @Input means it will accept value from parent component (AppComponent)
    @Input() user_id: any;
 
    user: User;
    roles: Role[];
 
    // initialize user service
    constructor(private userService: UserService,
                private roleService: RoleService,
                private alertService: AlertService){}
 
    // user clicks the 'read users' button
    readUsers(){
        this.show_read_users_event.emit({ title: "Read Users" });
    }

    // read roles from database
    ngOnInit(){
        this.userService.readOneUser(this.user_id)
            .subscribe(user => {
                    this.user=user;                  
                },
                error => {
                    this.alertService.error(error);
                }
            );

        this.roleService.readRoles()
            .subscribe(roles => {
                    this.roles=roles
                },
                error => {
                    this.alertService.error(error);
                }
            );
    }
}