import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterContactOutlet } from './components/master-contact-outlet/master-contact-outlet';
import { ContactList } from './components/contact-list/contact-list';


const routes: Routes = [
  {
    path: '',
    component: MasterContactOutlet,
    children: [
      { path: '', redirectTo: 'friend-list', pathMatch: 'full' },
      { path: 'friend-list', component: ContactList },
      { path: 'groups', component: ContactList },
      { path: 'friend-requests', component: ContactList },
      { path: 'group-invitations', component: ContactList },
      // { path: ':contactId', component: ChatBox },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterContactRoutingModule { }