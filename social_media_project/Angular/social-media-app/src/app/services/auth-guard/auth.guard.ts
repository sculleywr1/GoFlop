import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AlertService } from '../alert/alert.service';
import { UserService } from '../user/user.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router:Router, private userService: UserService, private alertService: AlertService){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      // console.log("from authguard "+ this.userService.currUser.username);
      // using this for now before connecting to backend
      if(this.userService.currUser.username !== undefined){
        return true;
    }
    this.router.navigate(['login']);
    // console.log("Access Denied!");
    this.alertService.error("Not Logged In");
    return false;
  }
}
