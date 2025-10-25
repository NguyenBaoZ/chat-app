import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared-module';
// import { MasterChatModule } from '../master-chat/master-chat-module';
import { UserProfileModule } from '../user-profile/user-profile-module';
import { MasterOutlet } from './components/master-outlet/master-outlet';
import { MasterPageRoutingModule } from './master-page-routing.module';


@NgModule({
  declarations: [
    MasterOutlet
  ],
  imports: [
    SharedModule,
    CommonModule,
    // MasterChatModule,
    UserProfileModule,
    MasterPageRoutingModule
  ],
  exports: [
    MasterOutlet
  ]
})
export class MasterPageModule { }
