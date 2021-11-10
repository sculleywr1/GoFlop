import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IUser } from '../../models/user';

@Injectable()
export class UserService {
  private currentUser = new IUser();

  constructor(private httpClient: HttpClient) { }

	set currUser(user:IUser) {
		this.currentUser = user;
		sessionStorage.setItem("user", JSON.stringify(this.currentUser));
	}

	get currUser():IUser {
		// return this.currentUser;
		let user: string = sessionStorage.getItem("user") || '{}';
		return JSON.parse(user);
	}
}





