import { Component } from '@angular/core';

@Component({
    moduleId: module.id.toString(),
    templateUrl: './transaction.component.html',
    styleUrls: ['./transaction.component.css']
})

export class TransactionsComponent {
    title = "Read Transactions";
    transaction_id: number;

    show_read_transaction_html = true;
    show_filter_transaction_html = false;

    showReadTransaction(event: any) {
        this.title = event.title;

        this.hideAll_Html();
        this.show_read_transaction_html = true;
    }

    showFilterTransaction(event: any){
        this.title = event.title;

        this.hideAll_Html;
        this.show_filter_transaction_html = true;
    }

    hideAll_Html() {
        this.show_read_transaction_html = false;
        this.show_filter_transaction_html = false;
    }
}