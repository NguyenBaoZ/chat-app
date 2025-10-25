import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterOutlet } from './components/master-outlet/master-outlet';


const routes: Routes = [
  {
    path: '',
    component: MasterOutlet,
    children: [
      { 
        path: 'chat', 
        loadChildren: () => import('../master-chat/master-chat-module').then(m => m.MasterChatModule) 
      },
      { 
        path: 'contacts', 
        loadChildren: () => import('../master-contact/master-contact-module').then(m => m.MasterContactModule) 
      },
      { path: '', redirectTo: 'chat', pathMatch: 'full' }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterPageRoutingModule { }