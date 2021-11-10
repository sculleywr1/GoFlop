import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IUser } from '../../models/user';
import { UserService } from '../user/user.service';

@Injectable()
export class RegisterService {
  submit(newUser: UserService) {
    throw new Error('Method not implemented.');
  }

  private url: string = "http://34.71.84.231:9005/socialMediaWebApp/api/userAccount/createAccount/";

  constructor(private httpCli: HttpClient) { }

  registerRequest(newUser: IUser) {
    return this.httpCli.post(this.url, newUser, {withCredentials: true});
  }
}


