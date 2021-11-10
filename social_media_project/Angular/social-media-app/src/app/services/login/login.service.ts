import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';


@Injectable()
export class LoginService {

  private url:string  = "http://34.71.84.231:9005/socialMediaWebApp/api/userAccount/login/";

  constructor(private httpCli:HttpClient) { }

  loginRequest(username: string, password: string){
    return this.httpCli.post(this.url, {username, password}, {withCredentials: true, responseType:"text",observe:"response"});
  }
}
