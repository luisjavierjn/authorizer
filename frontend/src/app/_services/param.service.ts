import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { OperationParam } from '../_models/index';
import { ConfigService } from './config.service';

@Injectable()
export class ParamService {
    constructor(private http: Http, private config: ConfigService) { }

    readOperationParams(): Observable<OperationParam[]>{
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/param/getalloperationparams', this.jwt()).map((response: Response) => response.json(), options);
    }

    readOneOperationParam(id: number) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/param/getoperationparambyid?id=' + id, this.jwt()).map((response: Response) => response.json(), options);
    }

    readOperationParamByCode(code: string) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/param/getoperationparambycode?code=' + code, this.jwt()).map((response: Response) => response.json(), options);
    }    

    readOperationParamByName(name: string) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/param/getoperationparambyname?name=' + name, this.jwt()).map((response: Response) => response.json(), options);
    }    

    readOperationParamListByCodes(codes: string[]) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/param/getoperationparamlistbycodes?codes=' + codes, this.jwt()).map((response: Response) => response.json(), options);
    } 

    updateOperationParam(operationParam: OperationParam) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.put(this.config.get('apiUrl') + 'api/param/updateoperationparam', operationParam, this.jwt()).map((response: Response) => response.json(), options);
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