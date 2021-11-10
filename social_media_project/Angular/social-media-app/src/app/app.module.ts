import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule, HttpParams} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LoginService } from './services/login/login.service';
import { UserService } from './services/user/user.service';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './components/register/register.component';
import { NgbActiveModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterService } from './services/register/register.service';
import { ReactiveFormsModule } from '@angular/forms';
import { AlertComponent } from './components/alert/alert.component';
import { AlertService } from './services/alert/alert.service';
import { HomeComponent } from './components/home/home.component';
import { PostComponent } from './components/post/post.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuard } from './services/auth-guard/auth.guard';
import { PostService } from './services/post/post.service';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireDatabaseModule } from '@angular/fire/compat/database';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { environment } from '../environments/environment';
import { FeedComponent } from './components/feed/feed.component';
import { NewPostComponent } from './components/new-post/new-post.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NavbarService } from './services/navbar/navbar.service';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ForgotPasswordService } from './services/forgot-password-service/forgot-password.service';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { PasswordTokenService } from './services/password-token/password-token.service';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { UpdateUserService } from './services/update-user/update-user.service';
import { SearchUserComponent } from './components/search-user/search-user.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    HomeComponent,
    PostComponent,
    ProfileComponent,
    FeedComponent,
    NewPostComponent,
    NavbarComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    EditProfileComponent,
    SearchUserComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireStorageModule,
  ],
  providers: [
    LoginService, 
    UserService, 
    RegisterService, 
    AlertService, 
    AuthGuard,
    PostService,
    NgbActiveModal,
    NavbarService,
    ForgotPasswordService,
    PasswordTokenService,
    UpdateUserService,
    {provide: LocationStrategy, useClass: HashLocationStrategy}
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
