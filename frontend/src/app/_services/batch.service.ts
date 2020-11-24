import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { BatchRecordHist } from '../_models/index';
import { ConfigService } from './config.service';

@Injectable()
export class BatchService {
    constructor(private http: Http, private config: ConfigService) { }

    readBatchRecordHist(): Observable<BatchRecordHist[]>{        
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/batch/getallrecordhist', options)
            .map((response: Response) => {
                var data = response.json()                
                data.forEach((d) => {
                    d.wr_date = new Date(d.wr_date);
                });
                return data;
            });
    }

    // se necesita probar esta función
    readOneBatchRecordHist(id: number) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/batch/getrecordhistbyid?id=' + id, options)
            .map((response: Response) => {
                var data = response.json()                
                data.forEach((d) => {
                    d.wr_date = new Date(d.wr_date);
                });
                return data;
            });
    }

    private jwt() {
        // create authorization header with jwt token
        let currentChannel = JSON.parse(localStorage.getItem('currentBatch'));
        if (currentChannel && currentChannel.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentChannel.token });
            return new RequestOptions({ headers: headers });
        }
    }

}