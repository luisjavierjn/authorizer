import { Component, OnInit, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { MerchantService } from '../../_services/merchant.service';
import { ChannelService } from '../../_services/channel.service';
import { Observable } from 'rxjs/Observable';
import { Merchant } from '../../_models/merchant';
import { Channel } from '../../_models/channel';

import { AlertService } from '../../_services/alert.service';
 
@Component({
    selector: 'app-read-one-merchant',
    templateUrl: './read-one-merchant.component.html',
    styleUrls: ['./read-one-merchant.component.css'],
    providers: [MerchantService]
})
 
export class ReadOneMerchantComponent implements OnInit {
 
    /*
        @Output will tell the parent component (AppComponent)
        that an event has happened (via .emit(), see readMerchants() method below)
    */
    @Output() show_read_merchants_event = new EventEmitter();
 
    // @Input means it will accept value from parent component (AppComponent)
    @Input() merchant_id: any;
 
    merchant: Merchant;
    channels: Channel[];
 
    // initialize merchant service
    constructor(private merchantService: MerchantService,
                private channelService: ChannelService,
                private alertService: AlertService){}
 
    // user clicks the 'read merchants' button
    readMerchants(){
        this.show_read_merchants_event.emit({ title: "Read Merchants" });
    }

    // read channels from database
    ngOnInit(){
        this.merchantService.readOneMerchant(this.merchant_id)
            .subscribe(merchant => {
                    this.merchant=merchant;                  
                },
                error => {
                    this.alertService.error(error);
                }
            );

        this.channelService.readChannels()
            .subscribe(channels => {
                    this.channels=channels
                },
                error => {
                    this.alertService.error(error);
                }
            );
    }
}