import { Component, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '../../_services/user.service';
import { RoleService } from '../../_services/role.service';
import { Observable } from 'rxjs/Observable';
import { Role } from '../../_models/role';
import { IActive } from '../../_models/active';
 
@Component({
    selector: 'app-update-user',
    templateUrl: './update-user.component.html',
    styleUrls: ['./update-user.component.css'],
    providers: [UserService, RoleService]
})
export class UpdateUserComponent implements OnChanges {
 
    // our angular form
    update_user_form: FormGroup;
 
    @Output() show_read_users_event = new EventEmitter();
    
    @Input() user_id: any;

    roles: Role[];
    actives: IActive[] = [{id:1, text:'active'},{id:0, text:'inactive'}]; 
 
    // initialize user & role services
    constructor(
        private userService: UserService,
        private roleService: RoleService,
        private formBuilder: FormBuilder
    ){
 
        // build angular form
        this.update_user_form = this.formBuilder.group({
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
    updateUser(){
 
        // add user_id in the object so it can be updated
        this.update_user_form.value.id = this.user_id;
 
        // send data to server
        this.userService.updateUser(this.update_user_form.value)
            .subscribe(
                 user => {
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
 
    // call the record when 'user_id' was changed
    ngOnChanges(){

        // read one user record
        this.userService.readOneUser(this.user_id)
            .subscribe(user => {
 
                // put values in the form
                this.update_user_form.patchValue({
                    id: user.id,
                    username: user.username,
                    phone_number: user.phone_number,
                    email: user.email,
                    area: user.area,
                    status: user.status,
                    fk_roles: user.fk_roles
                });
            });
    }
 
    // read roles from database
    ngOnInit(){
        this.roleService.readRoles()
            .subscribe(roles => this.roles=roles);
    }
}