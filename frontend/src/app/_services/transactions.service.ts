import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Transaction, TransactionFilter } from '../_models/index';
import { ConfigService } from './config.service';

@Injectable()
export class TransactionService {
    constructor(private http: Http, private config: ConfigService) { }

    readTransactions(): Observable<Transaction[]> {
        return this.getAll();
    }

    getAll() {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let options = new RequestOptions({ headers: headers });
        console.log("readTransactions");
        return this.http.get(this.config.get('apiUrl') + 'api/datatransaction/getall', this.jwt()).map((response: Response) => response.json(), options);
    }

    readAllTrasactions() {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        console.log("readAllTransactions");
        return this.http.get(this.config.get('apiUrl') + 'api/datatransaction/getall', options)
            .map((response: Response) => {
                var data = response.json()                
                data.forEach((d) => {
                    d.ex_date = new Date(d.ex_date);
                });
                return data;
            });
    }

    getByFilter(filters: TransactionFilter){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let options = new RequestOptions({ headers: headers });
        console.log("getByFilter");
        return this.http.post(this.config.get('apiUrl') + 'api/datatransaction/getfiltereddata', filters, options)
            .map((response: Response) => {
                var data = response.json()
                data.forEach((d) => {
                    d.tranDate = new Date(d.tranDate);
                    d.processDate = new Date(d.processDate);
                    d.lastUpdate = new Date(d.lastUpdate);
                });
                return data;
            });
    }

    getCsvFilePath(filters: TransactionFilter){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let options = new RequestOptions({ headers: headers });
        console.log("readTransactions");
        
        return this.http.post(this.config.get('apiUrl') + 'api/datatransaction/generatecvsfile', filters, options)
            .map(res => res.json());
    }

    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentMerchant'));
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
            return new RequestOptions({ headers: headers });
        }
    }
}

