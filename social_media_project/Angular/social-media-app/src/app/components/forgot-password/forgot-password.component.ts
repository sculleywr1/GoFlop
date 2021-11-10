import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert/alert.service';
import { ForgotPasswordService } from 'src/app/services/forgot-password-service/forgot-password.service';
import { PasswordTokenService } from 'src/app/services/password-token/password-token.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
   request_username!: string;
   request_email!: string;
  constructor(
    private passwordService: ForgotPasswordService,
    private router: Router,
    private passToken: PasswordTokenService) { }

  ngOnInit(): void {
  }

  submit() {
    this.passwordService.forgotPassword(this.request_username, this.request_email).subscribe(
      data => {
        console.log("data " +data);
        // set user if request does not return null
        if (data.statusText == "OK" && data.body!=null) {
          console.log("inside: " + data.body);
          this.passToken.passToken = data.body;
          this.router.navigate(["resetPassword"]);
        }
      }
    );
  }
 


}
