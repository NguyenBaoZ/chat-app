import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MasterContactOutlet } from './components/master-contact-outlet/master-contact-outlet';
import { MasterContactRoutingModule } from './master-contact-routing.module';
import { ContactMenu } from './components/contact-menu/contact-menu';
import { SharedModule } from '../shared/shared-module';
import { FriendList } from './components/friend-list/friend-list';



@NgModule({
  declarations: [
    MasterContactOutlet,
    ContactMenu,
    FriendList
  ],
  imports: [
    CommonModule,
    MasterContactRoutingModule,
    SharedModule
  ]
})
export class MasterContactModule { }
