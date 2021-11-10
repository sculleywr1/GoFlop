import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/post';
import { IUser } from 'src/app/models/user';

@Injectable({
  providedIn: 'root'
})
export class SearchUserService {
  private url:string  = "http://34.71.84.231:9005/socialMediaWebApp/api/userAccount/";
  constructor(private httpCli:HttpClient) { }

  getUserByName(username: string): Observable<IUser>{
    return this.httpCli.get<IUser>(this.url + `getUserByUsername/${username}`, {withCredentials: true});
  }
}
