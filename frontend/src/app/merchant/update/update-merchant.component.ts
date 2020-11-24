import { Component, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MerchantService } from '../../_services/merchant.service';
import { ChannelService } from '../../_services/channel.service';
import { Observable } from 'rxjs/Observable';
import { Channel } from '../../_models/channel';
import { IActive } from '../../_models/active';
 
@Component({
    selector: 'app-update-merchant',
    templateUrl: './update-merchant.component.html',
    styleUrls: ['./update-merchant.component.css'],
    providers: [MerchantService, ChannelService]
})
export class UpdateMerchantComponent implements OnChanges {
 
    // our angular form
    update_merchant_form: FormGroup;
 
    @Output() show_read_merchants_event = new EventEmitter();
    
    @Input() merchant_id: any;

    channels: Channel[];
    actives: IActive[] = [{id:1, text:'active'},{id:0, text:'inactive'}]; 
 
    // initialize merchant & channel services
    constructor(
        private merchantService: MerchantService,
        private channelService: ChannelService,
        private formBuilder: FormBuilder
    ){
 
        // build angular form
        this.update_merchant_form = this.formBuilder.group({
            merchantId: ["", Validators.required],
            merchantName: ["", Validators.required],
            accountHoganNo: ["", Validators.required],
            rucNo: ["", Validators.required],
            checkDigit: ["", Validators.required],
            percentageRetention: ["", Validators.required],
            commission: ["", Validators.required],
            commissionItbms: ["", Validators.required],
            status: ["", Validators.required],
            channel: ["", Validators.required]
        });
    }
 
    // user clicks 'create' button
    updateMerchant(){
 
        // add merchant_id in the object so it can be updated
        this.update_merchant_form.value.merchantId = this.merchant_id;
 
        // send data to server
        this.merchantService.updateMerchant(this.update_merchant_form.value)
            .subscribe(
                 merchant => {
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
 
    // call the record when 'merchant_id' was changed
    ngOnChanges(){
        // read one merchant record
        this.merchantService.readOneMerchant(this.merchant_id)
            .subscribe(merchant => {
 
                // put values in the form
                this.update_merchant_form.patchValue({
                    merchantId: merchant.merchantId,
                    merchantName: merchant.merchantName,
                    accountHoganNo: merchant.accountHoganNo,
                    rucNo: merchant.rucNo,
                    checkDigit: merchant.checkDigit,
                    percentageRetention: merchant.percentageRetention,
                    commission: merchant.commission,
                    commissionItbms: merchant.commissionItbms,
                    status: merchant.status,
                    channel: merchant.channel
                });
            });
    }
 
    // read channel from database
    ngOnInit(){
        this.channelService.readChannels()
            .subscribe(channels => this.channels=channels);
    }
}