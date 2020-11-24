import { Component, Input, Output, EventEmitter } from '@angular/core';
import { MerchantService } from '../../_services/merchant.service';
import { Observable } from 'rxjs/Observable';
import { Merchant } from '../../_models/merchant';
 
@Component({
    selector: 'app-delete-merchant',
    templateUrl: './delete-merchant.component.html',
    styleUrls: ['./delete-merchant.component.css'],
    providers: [MerchantService]
})
 
export class DeleteMerchantComponent {
 
    /*
        @Output will tell the parent component (AppComponent)
        that an event has happened (via .emit(), see readMerchants() method below)
    */
    @Output() show_read_merchants_event = new EventEmitter();
 
    // @Input enable getting the merchant_id from parent component (AppComponent)
    @Input() merchant_id: any;
 
    // initialize merchant service
    constructor(private merchantService: MerchantService){}
 
    // user clicks 'yes' button
    deleteMerchant(){
        //debugger;
        // delete data from database
        this.merchantService.deleteMerchant(this.merchant_id)
            .subscribe(
                 merchant => {
 
                    // show an alert to tell the user if user was created or not
                    console.log(merchant);
 
                    // go back to list of merchants
                    this.readMerchants();
                 },
                 error => console.log(error)
             );
    }
 
    // user clicks the 'read merchants' button
    readMerchants(){
        this.show_read_merchants_event.emit({ title: "Read Merchants" });
    }
}