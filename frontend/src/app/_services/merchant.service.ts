import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
//import { Observable } from 'rxjs/Observable';
import { Observable } from 'rxjs/Rx';
import { Merchant } from '../_models/index';
import { ConfigService } from './config.service';
//import 'rxjs/add/observable/throw';

@Injectable()
export class MerchantService {
    constructor(private http: Http, private config: ConfigService) { }

    readMerchants(): Observable<Merchant[]>{
        //return this.http
        //    .get("http://localhost:8080/api/users/getall")
        //    .map(res => res.json());
        return this.getAll();
    }

    getAll() {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        console.log("getAll")
        return this.http.get(this.config.get('apiUrl') + 'api/merchants/getall', this.jwt()).map((response: Response) => response.json(), options);
    }

    readMerchantsByName(name: string): Observable<Merchant[]>{
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/merchants/getbyname?merchant_name=' + name, this.jwt()).map((response: Response) => response.json(), options);
    }

    //devuelve un objeto merchant
    readOneMerchant(id: string) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/merchants/getbyid?merchant_id=' + id, this.jwt()).map((response: Response) => response.json(), options);
    }   

    //devuelve una lista con un unico merchant
    readMerchantById(id: string) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/merchants/getmerchantbyid?merchant_id=' + id, this.jwt()).map((response: Response) => response.json(), options);
    }       

    createMerchant(merchant: Merchant) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.post(this.config.get('apiUrl') + 'api/merchants/create', merchant, this.jwt()).map((response: Response) => response.json(), options);
    }     

    updateMerchant(merchant: Merchant) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.put(this.config.get('apiUrl') + 'api/merchants/update', merchant, this.jwt()).map((response: Response) => response.json(), options);
    }

    deleteMerchant(id: string) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.delete(this.config.get('apiUrl') + 'api/merchants/delbyid?merchant_id=' + id, this.jwt()).map((response: Response) => response.json(), options);
    }   

    //public uploadFile(file: File, onSuccess) {
    uploadFile(file: File) {
        let formData:FormData = new FormData();
        formData.append('uploadFile', file, file.name);
        let headers = new Headers();
        headers.append('enctype', 'multipart/form-data');
        headers.append('Accept', 'application/json');
        let options = new RequestOptions({ headers: headers });
        //debugger;
        this.http.post(this.config.get('apiUrl') + 'api/files/upload', formData, options)
        //this.http.post(this.config.get('apiUrl') + 'api/uploadTest?msg=formData', this.jwt()).map((response: Response) => response.json(), options)
            .map(res => res.json())
            .catch(error => Observable.throw(error))
            .subscribe(
                //data => onSuccess(data),
                data => console.log(data)//,
                //error => console.log(error)
            )
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