import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { ConfigService } from './config.service';
import * as FileSaver from 'file-saver'; 

@Injectable()
export class FileService {

    // We need Http to talk to a remote server.
    constructor(private http: Http, private config: ConfigService) { }

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

    downloadFile(path: string, type: string, name: string){
        
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'text/csv');
        let options = new RequestOptions({ headers: headers });
        options.responseType = ResponseContentType.Blob;
        return this.http.post(this.config.get('apiUrl') + 'api/files/download/' + type + '?file_path=' + path, null, options)
            .map((response: Response) => {
                //debugger;
                let fileBlob = response.blob();
                let blob = new Blob([fileBlob], { 
                    type: 'text/csv' // must match the Accept type
                });  
                let filename = name;
                FileSaver.saveAs(blob, filename);             
            });
    }
    
    downloadCsvFile(name: string, type: string){
        
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'text/csv');
        let options = new RequestOptions({ headers: headers });
        options.responseType = ResponseContentType.Blob;
        return this.http.post(this.config.get('apiUrl') + 'api/files/download/' + type + '?file_path=' + name, null, options)
            .map((response: Response) => {
                //debugger;
                let fileBlob = response.blob();
                let blob = new Blob([fileBlob], { 
                    type: 'text/csv' // must match the Accept type
                });  
                /*let filename = name;*/
                FileSaver.saveAs(blob, "download.csv");             
            });
    } 
}