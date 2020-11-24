import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { BatchService, ParamService, FileService, PagerService } from '../../_services';
import { Observable } from 'rxjs/Observable';
import { BatchRecordHist, OperationParam } from '../../_models/index';
 
@Component({
    selector: 'app-read-unified',
    templateUrl: './read-unified.component.html',
    styleUrls: ['./read-unified.component.css'],
    providers: [BatchService, ParamService]
})
 
export class ReadUnifiedComponent implements OnInit {

    @Output() show_read_one_unified_event=new EventEmitter();
 
    batches: BatchRecordHist[];

    params: OperationParam[];

    // pager object
    pager: any = {};

    // paged items
    pagedItems: any[];    

    constructor(private batchService: BatchService,
                private paramService: ParamService,
                private fileService: FileService,
                private pagerService: PagerService){}    

    readOneUnified(id: number){

        this.show_read_one_unified_event.emit({
            batch_id: id,
            title: "Read One Batch"
        });
    }

    downloadFile(name: string, type: string){
        
        var ruta = this.params[0].operation_value + type + '/' + name;
        //debugger;
        this.fileService.downloadFile(ruta,'external',name)
            .subscribe();
    }

    ngOnInit() {

        this.batchService.readBatchRecordHist()
            .subscribe(batches => {
                //debugger;
                this.batches = batches;

                // initialize to page 1
                this.setPage(1);                
            });

        this.paramService.readOperationParamListByCodes(['43'])
            .subscribe(params => {
                this.params = params;
            });
    }

    setPage(page: number) {
        if (page < 1 || page > this.pager.totalPages) {
            return;
        }

        // get pager object from service
        this.pager = this.pagerService.getPager(this.batches.length, page);

        // get current page of items
        this.pagedItems = this.batches.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }      
}