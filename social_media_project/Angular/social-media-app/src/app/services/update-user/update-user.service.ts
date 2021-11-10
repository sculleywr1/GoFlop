import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUser } from 'src/app/models/user';

@Injectable()
export class UpdateUserService {

  private url:string  = "http://34.71.84.231:9005/socialMediaWebApp/api/userAccount/updateProfile/";

  constructor(private httpCli:HttpClient) { }
  
  update(editUser: IUser) {
    // console.log("Update!!! "+ JSON.stringify(editUser));
    return this.httpCli.post(this.url, editUser, {withCredentials: true, responseType:"text",observe:"response"});
  }
}
