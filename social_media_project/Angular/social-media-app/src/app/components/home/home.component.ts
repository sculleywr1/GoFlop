import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { IUser } from 'src/app/models/user';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { UserService } from 'src/app/services/user/user.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  hasProfilePic: boolean = false;
  currentUser!: IUser;
  constructor(private userService: UserService, private title: Title,  public nav: NavbarService) { }

  ngOnInit() {
    this.title.setTitle('Home Page');
    this.nav.show();
    if(this.userService.currUser !== null) {
      this.currentUser = this.userService.currUser;
    }
  }



}
