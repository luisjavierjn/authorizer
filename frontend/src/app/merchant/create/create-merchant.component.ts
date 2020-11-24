import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MerchantService } from '../../_services/merchant.service';
import { ChannelService } from '../../_services/channel.service';
import { Observable } from 'rxjs/Observable';
import { Merchant } from '../../_models/merchant';
import { Channel } from '../../_models/channel';
import { IActive } from '../../_models/active';
 
@Component({
    selector: 'app-create-merchant',
    templateUrl: './create-merchant.component.html',
    styleUrls: ['./create-merchant.component.css'],
    providers: [MerchantService, ChannelService]
})
 
// component for creating a merchant record
export class CreateMerchantComponent {
 
    // our angular form
    create_merchant_form: FormGroup;
 
    // @Output will tell the parent component (AppComponent) that an event happened in this component
    @Output() show_read_merchants_event = new EventEmitter();
 
    // list of channels
    channels: Channel[];
    actives: IActive[] = [{id:1, text:'active'},{id:0, text:'inactive'}]; 
 
    // initialize 'merchant service', 'channel service' and 'form builder'
    constructor(
        private merchantService: MerchantService,
        private channelService: ChannelService,
        formBuilder: FormBuilder
    ){
        // based on our html form, build our angular form
        this.create_merchant_form = formBuilder.group({
            merchantId: ["", Validators.required],
            merchantName: ["", Validators.required],
            accountHoganNo: ["", Validators.required],
            rucNo: ["", Validators.required],
            checkDigit: ["", Validators.required],
            percentageRetention: ["", Validators.required],
            commission: ["", Validators.required],
            commissionItbms: ["", Validators.required],            
            channel: ["", Validators.required],
            status: ["", Validators.required]
        });
    }
 
    // user clicks 'create' button
    createMerchant(){
        //console.log(this.create_merchant_form.value);
 
        // send data to server
        this.merchantService.createMerchant(this.create_merchant_form.value)
            .subscribe(
                 merchant => {
                    //debugger;
                    // show an alert to tell the user if user was created or not
                    //console.log(merchant);
 
                    // go back to list of users
                    this.readMerchants();
                 },
                 error => console.log(error)
             );
    }
 
    // user clicks the 'read merchants' button
    readMerchants(){
        this.show_read_merchants_event.emit({ title: "Read Merchants" });
    }
 
    // what to do when this component were initialized
    ngOnInit(){
        // read categories from database
        this.channelService.readChannels()
            .subscribe(channels => this.channels=channels);
    }
}