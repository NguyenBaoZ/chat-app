import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared-module';
import { MasterChatOutlet } from './components/master-chat-outlet/master-chat-outlet';
import { ChatBox } from './components/chat-box/chat-box';
import { MasterChatRoutingModule } from './master-chat-routing.module';
import { ContactList } from './components/contact-list/contact-list';
import { ContactItem } from './components/contact-list/contact-item/contact-item';
import { DefaultWelcome } from './components/default-welcome/default-welcome';
import { ConversationWindow } from './components/chat-box/conversation-window/conversation-window';
import { MessageInput } from './components/chat-box/message-input/message-input';
import { ChatBubble } from './components/chat-box/conversation-window/chat-bubble/chat-bubble';
import { HeaderToolbar } from './components/chat-box/message-input/header-toolbar/header-toolbar';
import { MainInput } from './components/chat-box/message-input/main-input/main-input';
import { BoxHeader } from './components/chat-box/box-header/box-header';

@NgModule({
  declarations: [
    MasterChatOutlet,
    ChatBox,
    ContactList,
    ContactItem,
    DefaultWelcome,
    ConversationWindow,
    MessageInput,
    ChatBubble,
    HeaderToolbar,
    MainInput,
    BoxHeader
  ],
  imports: [
    SharedModule,
    MasterChatRoutingModule
  ],
  exports: [
    MasterChatOutlet,
    ChatBox,
    ContactList,
    ContactItem,
    DefaultWelcome,
    ConversationWindow,
    MessageInput,
    ChatBubble,
    HeaderToolbar,
    MainInput,
    BoxHeader
  ]
})
export class MasterChatModule { }
