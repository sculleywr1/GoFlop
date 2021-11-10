import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NewPostComponent } from './components/new-post/new-post.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { AuthGuard } from './services/auth-guard/auth.guard';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path:'home', component: HomeComponent, canActivate:[AuthGuard]},
  {path: "newPost", component: NewPostComponent,  canActivate:[AuthGuard]},
  {path: "profile", component: ProfileComponent,  canActivate:[AuthGuard]},
  {path:'register', component: RegisterComponent},
  {path: 'forgotPassword', component: ForgotPasswordComponent},
  {path: 'resetPassword', component: ResetPasswordComponent},
  {path:'logout', redirectTo: 'login'},
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
