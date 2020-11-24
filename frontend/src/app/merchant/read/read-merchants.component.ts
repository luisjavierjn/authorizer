import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MerchantService, ChannelService, PagerService } from '../../_services';
import { Observable } from 'rxjs/Observable';
import { Merchant, Channel } from '../../_models/index';
//import { FileUploader } from 'ng2-file-upload';

const URL = 'http://localhost:8080/authorizer/api/merchant/upload';
 
@Component({
    selector: 'app-read-merchants',
    templateUrl: './read-merchants.component.html',
    styleUrls: ['./read-merchants.component.css'],
    providers: [MerchantService, ChannelService, PagerService]
})
 
export class ReadMerchantsComponent implements OnInit {
    //public uploader: FileUploader = new FileUploader({url: URL});
    //public hasBaseDropZoneOver: boolean = false;
    //public hasAnotherDropZoneOver: boolean = false;

    //public fileOverBase(e: any): void {
    //    this.hasBaseDropZoneOver = e;
    //}

    //public fileOverAnother(e: any): void {
    //    this.hasAnotherDropZoneOver = e;
    //}

    search_merchant_form: FormGroup;

    filter: string;
    fvalue: string;

    /*
        * Needed to notify the 'consumer of this component', which is the 'MerchantComponent',
        that an 'event' happened in this component.
    */
    @Output() show_create_merchant_event=new EventEmitter();    
    @Output() show_read_one_merchant_event=new EventEmitter();
    @Output() show_update_merchant_event=new EventEmitter();
    @Output() show_delete_merchant_event=new EventEmitter();
    @Output() show_load_merchants_event=new EventEmitter();
 
    // store list of merchants
    merchants: Merchant[];

    // store list of channels
    channels: Channel[];

    // pager object
    pager: any = {};

    // paged items
    pagedItems: any[];    
 
    // initialize merchantService to retrieve list merchants in the ngOnInit()
    constructor(private merchantService: MerchantService,
                private channelService: ChannelService,
                private pagerService: PagerService,
                formBuilder: FormBuilder
    ){
        // based on our html form, build our angular form
        this.search_merchant_form = formBuilder.group({
            merchant_filter: ["", Validators.required],
            merchant_value: ""
        });
    }
 
    // methods that we will use later
    // when user clicks the 'create' button
    createMerchant(){
        // tell the parent component (AppComponent)
        this.show_create_merchant_event.emit({
            title: "Create Merchant"
        });
    }

    loadMerchants(){
        this.show_load_merchants_event.emit({
            title: "Load Merchants"
        });
    }

    // when user clicks the 'read' button
    readOneMerchant(id: string){
        //debugger;
        console.log('rp comp readOneMerchant');
        // tell the parent component (AppComponent)
        this.show_read_one_merchant_event.emit({
            merchant_id: id,
            title: "Read One Merchant"
        });
    }

    // when user clicks the 'update' button
    updateMerchant(id: string){
        //debugger;
        // tell the parent component (AppComponent)
        this.show_update_merchant_event.emit({
            merchant_id: id,
            title: "Update Merchant"
        });
    }

    // when user clicks the 'delete' button
    deleteMerchant(id: string){
        // tell the parent component (AppComponent)        
        this.show_delete_merchant_event.emit({
            merchant_id: id,
            title: "Delete Merchant"
        });
    } 
 
    searchMerchant(){

        this.filter = this.search_merchant_form.value.merchant_filter;
        this.fvalue = this.search_merchant_form.value.merchant_value;
        this.fvalue = this.fvalue.trim();

        //console.log('filter: ' + this.filter + ' fvalue: ' + this.fvalue);
        //debugger;

        if(this.filter != '' && this.fvalue != '') {
            if(this.filter == 'merchant_id') {
                this.merchantService.readMerchantById(this.fvalue)
                    .subscribe(merchants => {
                        this.merchants=merchants;

                        this.setPage(1);
                    });  
            }
            
            if(this.filter == 'merchant_name') {
                this.merchantService.readMerchantsByName(this.fvalue)
                    .subscribe(merchants => {
                        this.merchants=merchants;

                        this.setPage(1);
                    });    
            }
        }
        else {
            this.merchantService.readMerchants()
                .subscribe(merchants => {
                    this.merchants=merchants;

                    this.setPage(1);
                }); 
        }
    }

    uploadFile(event: any): void {
        // merchant_file
        let fileList: FileList = event.target.files;
        //debugger;
        if(fileList.length > 0) {
            //debugger;
            //mostrarBara
            //this.merchantService.uploadFile(fileList[0], onSUccessupoload.bind(this));
            this.merchantService.uploadFile(fileList[0]);
        }

    }

    //onSUccessupoload(data){
    //    console.log(data);
    //    ocultarBarra();
    //}


    // Read merchants from API.
    ngOnInit(){
        this.channelService.readChannels()
            .subscribe(channels => {
                this.channels=channels;
            });  
                    
        this.merchantService.readMerchants()
            .subscribe(merchants => {
                this.merchants=merchants;

                // initialize to page 1
                this.setPage(1);                
            });          
    }

    setPage(page: number) {
        if (page < 1 || page > this.pager.totalPages) {
            return;
        }

        // get pager object from service
        this.pager = this.pagerService.getPager(this.merchants.length, page);

        // get current page of items
        this.pagedItems = this.merchants.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }          
}