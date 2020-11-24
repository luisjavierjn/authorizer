import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { AutomJobsHist } from '../_models/index';
import { ConfigService } from './config.service';

@Injectable()
export class AutomJobsService {
    constructor(private http: Http, private config: ConfigService) { }

    readAutomJobs() {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/automjobs/getallautomjobshist', options)
            .map((response: Response) => {
                var data = response.json()                
                data.forEach((d) => {
                    d.ex_date = new Date(d.ex_date);
                });
                return data;
            });
    }

    readAutomJobsByDateRange(dateStart: string, dateEnd: string) {
        debugger;
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/automjobs/getautomjobshistbydaterange?dateStart=' + dateStart + '&dateEnd=' + dateEnd, options)
            .map((response: Response) => {
                //debugger;
                var data = response.json()                
                data.forEach((d) => {
                    d.ex_date = new Date(d.ex_date);
                });
                return data;
            });
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