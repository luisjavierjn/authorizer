import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { ConfigService } from './config.service';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    respuesta: any;
    constructor(private http: Http, private config: ConfigService) { }

    login(username: string, password: string) {
        //debugger;
        //return this.http.post('/api/authenticate', JSON.stringify({ username: username, password: password }))
        console.log(username + ' ' /*+ password*/);

        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});

        //var params = [{username: username, password: password}];
        var params = {'username': username, 'password': password};
        var url = this.config.get('apiUrl') + 'authenticate';

        return this.http.post(url, params, options)
            .map((response: Response) => {
                //debugger;
                console.log("Response: " + response);
                // login successful if there's a jwt token in the response
                let user = response.json();
                if (user) {// && user.token) {
                    console.log("user.token:" + user.token);
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
                /*console.log("username:" + user.username + " - password:" + user.password);*/
            });                 
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}