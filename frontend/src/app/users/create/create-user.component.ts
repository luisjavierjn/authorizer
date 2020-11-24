import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '../../_services/user.service';
import { RoleService } from '../../_services/role.service';
import { Observable } from 'rxjs/Observable';
import { User } from '../../_models/user';
import { Role } from '../../_models/role';
import { IActive } from '../../_models/active';
 
@Component({
    selector: 'app-create-user',
    templateUrl: './create-user.component.html',
    styleUrls: ['./create-user.component.css'],
    providers: [UserService, RoleService]
})
 
// component for creating a user record
export class CreateUserComponent {
 
    // our angular form
    create_user_form: FormGroup;
 
    // @Output will tell the parent component (AppComponent) that an event happened in this component
    @Output() show_read_users_event = new EventEmitter();
 
    // list of roles
    roles: Role[];
    actives: IActive[] = [{id:1, text:'active'},{id:0, text:'inactive'}]; 
 
    // initialize 'user service', 'role service' and 'form builder'
    constructor(
        private userService: UserService,
        private roleService: RoleService,
        formBuilder: FormBuilder
    ){
        // based on our html form, build our angular form
        this.create_user_form = formBuilder.group({
            id: ["", Validators.required],
            username: ["", Validators.required],
            phone_number: ["", Validators.required],
            email: ["", Validators.required],
            area: ["", Validators.required],
            status: ["", Validators.required],
            fk_roles: ["", Validators.required]            
        });
    }
 
    // user clicks 'create' button
    createUser(){
        console.log(this.create_user_form.value);
 
        // send data to server
        this.userService.createUser(this.create_user_form.value)
            .subscribe(
                 user => {
                    //debugger;
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
 
    // what to do when this component were initialized
    ngOnInit(){
        // read categories from database
        this.roleService.readRoles()
            .subscribe(roles => this.roles=roles);
    }
}