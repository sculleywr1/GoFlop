import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUser } from 'src/app/models/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private serveUrl : string = "";
  constructor(private httpClient : HttpClient) {
    this.serveUrl = `http://34.71.84.231:9005/socialMediaWebApp/api/userAccount/getUserById/`;
  }

  getUserById(userId: String): Observable<IUser>
  {
    return this.httpClient.get<IUser>(this.serveUrl + userId)
  }


}
