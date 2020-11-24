import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { ConfigService } from './config.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Role } from '../_models/index';
 
@Injectable()
 
// Service for categories data.
export class RoleService {
 
    // We need Http to talk to a remote server.
    constructor(private http: Http, private config: ConfigService) { }
 
    // Get list of categories from database via api.
    readRoles(): Observable<Role[]>{
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        console.log("getAll")
        return this.http.get(this.config.get('apiUrl') + 'api/roles/getall', this.jwt()).map((response: Response) => response.json(), options);
    }

    private jwt() {
        // create authorization header with jwt token
        let currentRole = JSON.parse(localStorage.getItem('currentRole'));
        if (currentRole && currentRole.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentRole.token });
            return new RequestOptions({ headers: headers });
        }
    }     
}