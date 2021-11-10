import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/app/services/register/register.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IUser } from 'src/app/models/user';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  newFirstName!:string;
  newLastName!:string;
  newEmail!:string;
  newUsername!:string;
  newPassword1!:string;
  newPassword2!:string;

  constructor(
      private regService:RegisterService, 
      public activeModal: NgbActiveModal
  ) { }

  ngOnInit() {
    //   console.log("from register");
  }

  submit():void{
    //   console.log("submitting new user info...");
      let newUser = new IUser();
      newUser.firstName = this.newFirstName;
      newUser.lastName = this.newLastName;
      newUser.userEmail = this.newEmail;
      newUser.username = this.newUsername;
      newUser.password = this.newPassword1;
      if(newUser.password === this.newPassword2){
          this.regService.registerRequest(newUser).subscribe(
              data=>{
                  console.log("register success!");
              }
          );
      }
      else{
          console.log("passwords did not match")
          return;
      }
      
  }
}
