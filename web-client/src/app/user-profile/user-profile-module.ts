import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserToolbar } from './user-toolbar/user-toolbar';
import { SharedModule } from '../shared/shared-module';

@NgModule({
  declarations: [
    UserToolbar
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    UserToolbar
  ]
})
export class UserProfileModule { }