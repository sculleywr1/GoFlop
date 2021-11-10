import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IUser } from 'src/app/models/user';
import { AlertService } from 'src/app/services/alert/alert.service';
import { LoginService } from 'src/app/services/login/login.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { UserService } from 'src/app/services/user/user.service';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //  from form
  login_username!: string;
  login_password!: string;

  //  user obj
  newUser = new IUser();

  constructor(
    private loginService: LoginService, 
    private userService: UserService, 
    private regModal: NgbModal, 
    private alertService: AlertService, 
    private router: Router,
    public nav: NavbarService) { }

  ngOnInit(): void {
    this.userService.currUser = (JSON.parse('{}'));
    this.nav.hide();
  }

  validate(): void {

    // send request to backend by username and password
    this.loginService.loginRequest(this.login_username, this.login_password).subscribe(
      data => {
        // console.log("data " +data);
        // set user if request does not return null
        if (data.statusText == "OK" && data.body!=null) {
          // console.log("inside");
          let dataJSON = JSON.parse(data.body);
          this.newUser.userId = dataJSON['userId'];
          this.newUser.firstName = dataJSON['firstName'];
          this.newUser.lastName = dataJSON['lastName'];
          this.newUser.userEmail = dataJSON['userEmail'];
          this.newUser.profilePicURL = dataJSON['profilePicURL'];
          this.newUser.username = dataJSON['username'];
          // this.newUser.level = dataJSON[''];
          // this.newUser.class1 =dataJSON[''];
          // this.newUser.race = dataJSON[''];
          this.userService.currUser = this.newUser;
          // redirect them to app-home
          this.router.navigate(["home"]);
        }
      },err=>{
        //pop alert
        this.error("Wrong Password!");
        // console.log(err.status);
    }
    );
  }

  register() {
    const modalRef = this.regModal.open(RegisterComponent);
    // console.log("register");
  }

  forgotPassword() {
    // console.log("forgotPassword");
    this.router.navigate(["forgotPassword"]);
  }

  error(message: string) {
    this.alertService.error(message);
  }

}
