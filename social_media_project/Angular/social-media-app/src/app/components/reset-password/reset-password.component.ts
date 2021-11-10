import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/models/user';
import { ForgotPasswordService } from 'src/app/services/forgot-password-service/forgot-password.service';
import { PasswordTokenService } from 'src/app/services/password-token/password-token.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  passwordToken!:string;
  new_password1!:string
  new_password2!:string
  reset_code!: string;

  constructor(private router:Router, private passTokenServ: PasswordTokenService, private passServ: ForgotPasswordService, private currAcc: UserService) { }

  ngOnInit(): void {
    // console.log("passToken: " + this.passTokenServ.passToken);
    this.passwordToken = this.passTokenServ.passToken;
  }

  resetPassword(){
    if(this.new_password1 === this.new_password2 && this.reset_code == this.passwordToken){
        // console.log("reset password! " + this.currAcc.currUser.username);
        let userAcc = new IUser;
        userAcc.password = this.new_password1;
        this.passServ.resetPassword(userAcc).subscribe(
            (data:any)=>{ 
              // console.log(data);
              this.router.navigate(["login"]);
            }
        );
    }
    else{
        console.log("password does not match");
    }
}

}
