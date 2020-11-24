import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { TransactionService, FileService, PagerService } from '../../_services';
import { Observable } from 'rxjs/Observable';
import { Transaction, TransactionFilter } from '../../_models/index';

@Component({
    selector: 'app-read-transactions',
    templateUrl: './read-transaction.component.html',
    styleUrls: ['./read-transaction.component.css'],
    providers: [TransactionService, PagerService]
})

export class ReadTransactionComponent implements OnInit {

    search_transaction_form: FormGroup;

    transactions: Transaction[];
    // pager object
    pager: any = {};
    // paged items
    pagedItems: any[];

    filters: TransactionFilter;
    filesFilter: TransactionFilter;

    merchant: string;
    terminalId: string;
    rrnTokenTrans: string;
    tranDateFrom: number;
    tranDateTo: number;
    lotNo: number;
    date1: Date;
    date2: Date;

    @Output() show_filter_transaction_event = new EventEmitter();

    constructor(private transactionService: TransactionService,
                private fileService: FileService,
                private pagerService: PagerService,
                formBuilder: FormBuilder
    ) {
        this.search_transaction_form = formBuilder.group({
            merchant_id: "",
            terminal_id: "",
            rrn_token_trans: "",
            lot_no: "",
            tran_date_from: "",
            tran_date_to: ""
        });
    }

    ngOnInit() {
        this.transactionService.readAllTrasactions()
            .subscribe(transactions => {
                this.transactions = transactions;
                this.setPage(1);
            });
    }

    searchTransaction() {
        
        this.merchant = this.search_transaction_form.value.merchant_id;
        this.terminalId = this.search_transaction_form.value.terminal_id;
        this.rrnTokenTrans = this.search_transaction_form.value.rrn_token_trans;
        this.date1 = this.search_transaction_form.value.tran_date_from;
        this.date2 = this.search_transaction_form.value.tran_date_to;
        this.lotNo = this.search_transaction_form.value.lot_no;

        var fecha1 = new Date(this.date1).getTime();
        var fecha2 = new Date(this.date2).getTime();
        /*console.log(fecha1);
        console.log(fecha2);*/
        if(fecha1 == null){
            fecha1 = 0;
        }
        if(fecha2 == null){
            fecha2 = 0;
        }
        if(this.merchant == ""){
            this.merchant = null;
        }
        if(this.terminalId == ""){
            this.terminalId = null;
        }
        if(this.rrnTokenTrans == ""){
            this.rrnTokenTrans = null;
        }
        if(this.lotNo == 0){
            this.lotNo = null;
        }
        /*debugger*/;
        if (fecha1 != 0 || fecha2 != 0 ) {
            this.filters = new TransactionFilter(this.merchant, this.terminalId, this.rrnTokenTrans, fecha1, fecha2, this.lotNo);
            /*debugger*/;
            this.transactionService.getByFilter(this.filters)
                .subscribe(transactions => {
                    this.transactions = transactions;
                    console.log("OK");
                });
                this.setPage(1);
            this.filesFilter = new TransactionFilter(this.merchant, this.terminalId, this.rrnTokenTrans, fecha1, fecha2, this.lotNo);
        } else {
            console.log("Error en validación.");
            this.setPage(1);
        }
    }

    downloadFile() {
        /*this.filters = this.searchTransaction();*/
        if (this.filesFilter != null) {
            console.log("Generando archivo...");
            console.log(this.filesFilter);
            this.transactionService.getCsvFilePath(this.filesFilter)
                .subscribe(data => this.generateFile(data));
        } else {
            console.log("Valide nuevamente el valor los campos.");
        }
    }

    generateFile(valor: string) {
        console.log(valor);
        if (valor != null || valor == undefined) {
            console.log("Valor ok");
            debugger;
            this.fileService.downloadCsvFile(valor, "external")
                .subscribe();
        }

    }

    setPage(page: number) {
/*        debugger;*/
        if (page < 1 || page > this.pager.totalPages) {
            return;
        }

        // get pager object from service
        this.pager = this.pagerService.getPager(this.transactions.length, page);

        // get current page of items
        this.pagedItems = this.transactions.slice(this.pager.startIndex, this.pager.endIndex + 1);
    } 

}