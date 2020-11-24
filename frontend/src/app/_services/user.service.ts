import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../_models/index';
import { ConfigService } from './config.service';

@Injectable()
export class UserService {
    constructor(private http: Http, private config: ConfigService) { }

    readUsers(): Observable<User[]>{
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
        return this.http.get(this.config.get('apiUrl') + 'api/users/getall', this.jwt()).map((response: Response) => response.json(), options);
    }

    readUserByUsername(username: string) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/users/getuserbyusername?username=' + username, this.jwt()).map((response: Response) => response.json(), options);
    }

    readUserById(id: number) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/users/getuserbyid?id=' + id, this.jwt()).map((response: Response) => response.json(), options);
    }

    readOneUser(id: number) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.get(this.config.get('apiUrl') + 'api/users/getbyid?id=' + id, this.jwt()).map((response: Response) => response.json(), options);
    }

    createUser(user: User) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.post(this.config.get('apiUrl') + 'api/users/create', user, this.jwt()).map((response: Response) => response.json(), options);
    }

    updateUser(user: User) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.put(this.config.get('apiUrl') + 'api/users/update', user, this.jwt()).map((response: Response) => response.json(), options);
    }

    deleteUser(id: number) {
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        return this.http.delete(this.config.get('apiUrl') + 'api/users/delbyid?id=' + id, this.jwt()).map((response: Response) => response.json(), options);
    }


    /*****************************************************************************************************/


    // Get a user details from remote server.
    readOneUser2(user_id: number): Observable<User>{
        return this.http
            .get("http://localhost/api/user/read_one.php?id="+user_id)
            .map(res => res.json());
    }

    // Send user data to remote server to create it.
    create(user: User): Observable<User>{    
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });    
        return this.http.post(
            "http://localhost/api/user/create.php",
            user,
            options
        ).map(res => res.json());
    }    

    updateUser3(id: number) {
    }

    // Send user data to remote server to update it.
    updateUser2(user: User): Observable<User>{
    
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
    
        return this.http.post(
            "http://localhost/api/user/update.php",
            user,
            options
        ).map(res => res.json());
    }        

    // Send user ID to remote server to delete it.
    deleteUser2(user_id: number){
    
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
    
        return this.http.post(
            "http://localhost/api/user/delete.php",
            { id: user_id },
            options
        ).map(res => res.json());
    }    

    // private helper methods

    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
            return new RequestOptions({ headers: headers });
        }
    }
}