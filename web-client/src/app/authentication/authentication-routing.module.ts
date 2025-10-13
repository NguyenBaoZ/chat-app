import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Login } from './components/login/login';
import { ForgotMyPassword } from './components/forgot-my-password/forgot-my-password';
import { AuthenticationOutlet } from './components/authentication-outlet/authentication-outlet';

const routes: Routes = [
  {
    path: '',
    component: AuthenticationOutlet,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: Login },
      { path: 'forgot-password', component: ForgotMyPassword }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthenticationRoutingModule { }