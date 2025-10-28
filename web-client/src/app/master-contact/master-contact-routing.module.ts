import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterContactOutlet } from './components/master-contact-outlet/master-contact-outlet';
import { FriendList } from './components/friend-list/friend-list';


const routes: Routes = [
  {
    path: '',
    component: MasterContactOutlet,
    children: [
      { path: '', redirectTo: 'friend-list', pathMatch: 'full' },
      { path: 'friend-list', component: FriendList },
      { path: 'groups', component: FriendList },
      { path: 'friend-requests', component: FriendList },
      { path: 'group-invitations', component: FriendList },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterContactRoutingModule { }