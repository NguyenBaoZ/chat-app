import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterChatOutlet } from './components/master-chat-outlet/master-chat-outlet';
import { ChatBox } from './components/chat-box/chat-box';
import { DefaultWelcome } from './components/default-welcome/default-welcome';


const routes: Routes = [
  {
    path: '',
    component: MasterChatOutlet,
    children: [
      { path: '', redirectTo: 'default', pathMatch: 'full' },
      { path: 'default', component: DefaultWelcome },
      { path: ':contactId', component: ChatBox },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterChatRoutingModule { }