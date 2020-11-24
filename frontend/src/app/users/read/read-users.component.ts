import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService, RoleService, PagerService } from '../../_services';
import { Observable } from 'rxjs/Observable';
import { User, Role } from '../../_models/index';
 
@Component({
    //moduleId: module.id.toString(),
    selector: 'app-read-users',
    templateUrl: './read-users.component.html',
    styleUrls: ['./read-users.component.css'],
    providers: [UserService, RoleService, PagerService]
})
 
export class ReadUsersComponent implements OnInit {

    search_users_form: FormGroup;

    filter: string;
    fvalue: string;    

    /*
        * Needed to notify the 'consumer of this component', which is the 'UserComponent',
        that an 'event' happened in this component.
    */
    @Output() show_create_user_event=new EventEmitter();    
    @Output() show_read_one_user_event=new EventEmitter();
    @Output() show_update_user_event=new EventEmitter();
    @Output() show_delete_user_event=new EventEmitter();    
 
    // store list of users
    users: User[];

    // store list of roles
    roles: Role[];

    // pager object
    pager: any = {};

    // paged items
    pagedItems: any[];      
 
    // initialize userService to retrieve list users in the ngOnInit()
    constructor(private userService: UserService,
                private roleService: RoleService,
                private pagerService: PagerService,
                formBuilder: FormBuilder){

        // based on our html form, build our angular form
        this.search_users_form = formBuilder.group({
            user_filter: ["", Validators.required],
            user_value: ""
        });                    
    }
 
    // methods that we will use later
    // when user clicks the 'create' button
    createUser(){
        // tell the parent component (AppComponent)
        this.show_create_user_event.emit({
            title: "Create User"
        });
    }

    // when user clicks the 'read' button
    readOneUser(id: number){
        //debugger;
        console.log('rp comp readOneUser');
        // tell the parent component (AppComponent)
        this.show_read_one_user_event.emit({
            user_id: id,
            title: "Read One User"
        });
    }

    // when user clicks the 'update' button
    updateUser(id: number){
        // tell the parent component (AppComponent)
        this.show_update_user_event.emit({
            user_id: id,
            title: "Update User"
        });
    }

    // when user clicks the 'delete' button
    deleteUser(id: number){
        // tell the parent component (AppComponent)        
        this.show_delete_user_event.emit({
            user_id: id,
            title: "Delete User"
        });
    } 

    searchUsers(){
        this.filter = this.search_users_form.value.user_filter;
        this.fvalue = this.search_users_form.value.user_value;
        this.fvalue = this.fvalue.trim();
        //debugger;
        if(this.filter != '' && this.fvalue != '') {
            //debugger;
            if(this.filter == 'peoplesoft') {
                this.userService.readUserById(parseInt(this.fvalue))
                    .subscribe(users => {
                        this.users = users;
                        //debugger;
                        this.setPage(1);
                    });  
            }
            
            if(this.filter == 'username') {
                this.userService.readUserByUsername(this.fvalue)
                    .subscribe(users => {
                        this.users = users;

                        this.setPage(1);
                    });    
            }
        }
        else {
            this.userService.readUsers()
                .subscribe(users => {
                    this.users=users;

                    this.setPage(1);
                }); 
        }                
    }
 
    // Read users from API.
    ngOnInit(){
        //debugger;
        this.roleService.readRoles()
            .subscribe(roles => {
                this.roles=roles;
            });  
                    
        this.userService.readUsers()
            .subscribe(users => {
                this.users=users;

                // initialize to page 1
                this.setPage(1);                
            });          
    }

    setPage(page: number) {
        if (page < 1 || page > this.pager.totalPages) {
            return;
        }

        // get pager object from service
        this.pager = this.pagerService.getPager(this.users.length, page);

        // get current page of items
        this.pagedItems = this.users.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }      
}