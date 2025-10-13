import { NgModule } from '@angular/core';
import { Login } from './components/login/login';
import { ForgotMyPassword } from './components/forgot-my-password/forgot-my-password';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import { AuthenticationOutlet } from './components/authentication-outlet/authentication-outlet';
import { SharedModule } from '../shared/shared-module';

@NgModule({
  declarations: [
    Login,
    ForgotMyPassword,
    AuthenticationOutlet
  ],
  imports: [
    AuthenticationRoutingModule,
    SharedModule,
  ]
})
export class AuthenticationModule { }
