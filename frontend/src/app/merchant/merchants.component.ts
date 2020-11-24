import { Component } from '@angular/core';

@Component({
    moduleId: module.id.toString(),
    templateUrl: './merchants.component.html',
    styleUrls: ['./merchants.component.css']
})

export class MerchantsComponent {
    // properties for child components
    title="Read Merchants";
    merchant_id: number;
 
    // properties used to identify what views to show
    show_read_merchants_html=true;
    show_create_merchant_html=false;
    show_read_one_merchant_html=false;
    show_update_merchant_html=false;
    show_delete_merchant_html=false;    

    // show the 'create merchant form'
    showCreateMerchant(event: any){
    
        // set title
        this.title=event.title;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_create_merchant_html=true;
    }
    
    // show merchants list
    showReadMerchants(event: any){        
        // set title
        this.title=event.title;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_read_merchants_html=true;
    }

    // show details of a merchant
    showReadOneMerchant(event: any){
    
        // set title and merchant ID
        this.title=event.title;
        this.merchant_id=event.merchant_id;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_read_one_merchant_html=true;
    }    

    // show the 'update merchant form'
    showUpdateMerchant(event: any){
    
        // set title and merchant ID
        this.title=event.title;
        this.merchant_id=event.merchant_id;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_update_merchant_html=true;
    }    

    // show 'are you sure?' prompt to confirm deletion of a record
    showDeleteMerchant(event: any){
    
        // set title and merchant ID
        this.title=event.title;
        this.merchant_id=event.merchant_id;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_delete_merchant_html=true;
    }

    // hide all html views
    hideAll_Html(){
        this.show_read_merchants_html=false;
        this.show_read_one_merchant_html=false;
        this.show_create_merchant_html=false;
        this.show_update_merchant_html=false;
        this.show_delete_merchant_html=false;
    }      
}
