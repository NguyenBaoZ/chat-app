import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MasterContactOutlet } from './components/master-contact-outlet/master-contact-outlet';
import { MasterContactRoutingModule } from './master-contact-routing.module';
import { ContactMenu } from './components/contact-menu/contact-menu';
import { SharedModule } from '../shared/shared-module';
import { ContactList } from './components/contact-list/contact-list';



@NgModule({
  declarations: [
    MasterContactOutlet,
    ContactMenu,
    ContactList
  ],
  imports: [
    CommonModule,
    MasterContactRoutingModule,
    SharedModule
  ]
})
export class MasterContactModule { }
