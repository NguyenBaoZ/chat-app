import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-toolbar',
  standalone: false,
  templateUrl: './user-toolbar.html',
  styleUrl: './user-toolbar.scss'
})
export class UserToolbar {
  constructor(private router: Router, private route: ActivatedRoute) { }

  currentPage: string = '';

  openProfile(): void {
    console.log('Opening profile...');
  }

  logout(): void {
    console.log('Logging out...');
  }

  navigateToChat(): void {
    this.router.navigate(['/master/chat']);
  }

  navigateToContacts(): void {
    this.router.navigate(['/master/contacts']);
  }
}
