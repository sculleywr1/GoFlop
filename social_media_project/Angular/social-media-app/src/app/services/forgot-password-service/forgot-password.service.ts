import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IUser } from 'src/app/models/user';

@Injectable()
export class ForgotPasswordService {

  private url:string  = "http://34.71.84.231:9005/socialMediaWebApp/api/userAccount/";

  constructor(private httpCli:HttpClient) { }

  forgotPassword(username: string, userEmail: string){
    // console.log(`username and password: ${username + " " + userEmail}`);
    return this.httpCli.post(this.url + `forgotPassword`, {username, userEmail}, {withCredentials: true, responseType:"text", observe:"response"});
  }

  resetPassword(userAcc: IUser) {
    // console.log(JSON.stringify(userAcc));
    return this.httpCli.post(this.url + `updatePassword`, userAcc, {withCredentials: true, responseType:"text", observe:"response"});
  }
}
