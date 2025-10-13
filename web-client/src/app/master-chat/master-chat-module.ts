import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared-module';
import { MasterChatOutlet } from './components/master-chat-outlet/master-chat-outlet';
import { ChatBox } from './components/chat-box/chat-box';
import { MasterChatRoutingModule } from './master-chat-routing.module';
import { ContactList } from './components/contact-list/contact-list';
import { ContactItem } from './components/contact-list/contact-item/contact-item';
import { DefaultWelcome } from './components/default-welcome/default-welcome';

@NgModule({
  declarations: [
    MasterChatOutlet,
    ChatBox,
    ContactList,
    ContactItem,
    DefaultWelcome
  ],
  imports: [
    SharedModule,
    MasterChatRoutingModule
  ],
  exports: [ 
    MasterChatOutlet,
    ChatBox
  ]
})
export class MasterChatModule { }
