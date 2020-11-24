import { Component } from '@angular/core';
 
@Component({
    moduleId: module.id.toString(),
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css']
})
 
export class UsersComponent {
    // properties for child components
    title="Read Users";
    user_id: number;
 
    // properties used to identify what views to show
    show_read_users_html=true;
    show_create_user_html=false;
    show_read_one_user_html=false;
    show_update_user_html=false;
    show_delete_user_html=false;    

    // show the 'create user form'
    showCreateUser(event: any){
    
        // set title
        this.title=event.title;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_create_user_html=true;
    }
    
    // show users list
    showReadUsers(event: any){        
        // set title
        this.title=event.title;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_read_users_html=true;
    }

    // show details of a user
    showReadOneUser(event: any){
    
        // set title and user ID
        this.title=event.title;
        this.user_id=event.user_id;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_read_one_user_html=true;
    }    

    // show the 'update user form'
    showUpdateUser(event: any){
    
        // set title and user ID
        this.title=event.title;
        this.user_id=event.user_id;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_update_user_html=true;
    }    

    // show 'are you sure?' prompt to confirm deletion of a record
    showDeleteUser(event: any){
    
        // set title and user ID
        this.title=event.title;
        this.user_id=event.user_id;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_delete_user_html=true;
    }         
    
    // hide all html views
    hideAll_Html(){
        this.show_read_users_html=false;
        this.show_read_one_user_html=false;
        this.show_create_user_html=false;
        this.show_update_user_html=false;
        this.show_delete_user_html=false;
    }      
}

