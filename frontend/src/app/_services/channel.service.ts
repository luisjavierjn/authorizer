import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Channel } from '../_models/index';
import { ConfigService } from './config.service';
 
@Injectable()
 
// Service for categories data.
export class ChannelService {
 
    // We need Http to talk to a remote server.
    constructor(private http: Http, private config: ConfigService) { }
 
    // Get list of categories from database via api.
    readChannels(): Observable<Channel[]>{
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        console.log("getAll")
        return this.http.get(this.config.get('apiUrl') + 'api/channels/getall', this.jwt()).map((response: Response) => response.json(), options);
    }

    private jwt() {
        // create authorization header with jwt token
        let currentChannel = JSON.parse(localStorage.getItem('currentChannel'));
        if (currentChannel && currentChannel.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentChannel.token });
            return new RequestOptions({ headers: headers });
        }
    }
}