import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../../shared/services/users.servive';

@Component({
  selector: 'app-master-chat-outlet',
  standalone: false,
  templateUrl: './master-chat-outlet.html',
  styleUrl: './master-chat-outlet.scss'
})
export class MasterChatOutlet implements OnInit{
  constructor(private userService: UsersService) {}

  ngOnInit() {
    this.userService.getContactsList().subscribe(contacts => {
      console.log('Contacts:', contacts);
    });
  }
}
