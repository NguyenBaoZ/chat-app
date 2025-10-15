import { Component, Input } from '@angular/core';
import { Contact } from '../../models/contact.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-contact-list',
  standalone: false,
  templateUrl: './contact-list.html',
  styleUrl: './contact-list.scss'
})
export class ContactList {
  @Input() contacts: Contact[] = [{
    id: '1',
    name: 'NA',
    // avatarUrl: 'https://example.com/avatar1.png',
    email: 'na@iii.ccc',
    lastMessage: 'Hello!',
    lastMessageTime: new Date()
  }];
  @Input() selectedContactId: string | null = null;
  @Input() isLoading: boolean = false;


  constructor(private router: Router) {}


  selectedContact: Contact | null = null;
  contextMenuContact: Contact | null = null;

  onContactSelected(contact: Contact): void {
    this.selectedContact = contact;
    console.log('Selected contact:', contact);
    this.router.navigate(['/chat', contact.id]);
  }

  onMenuClicked(data: { event: Event, contact: Contact }): void {
    this.contextMenuContact = data.contact;
    // You could open a context menu here if needed
  }

  onEditContact(contact: Contact): void {
    console.log('Edit contact:', contact);
    // Implement edit functionality
  }

  onDeleteContact(contact: Contact): void {
    if (confirm(`Are you sure you want to delete ${contact.name}?`)) {
      this.contacts = this.contacts.filter(c => c.id !== contact.id);
      if (this.selectedContact?.id === contact.id) {
        this.selectedContact = null;
      }
      console.log('Deleted contact:', contact);
    }
  }

  addNewContact(): void {
    console.log('Add new contact');
    // Implement add functionality
  }

  searchContacts(): void {
    console.log('Search contacts');
    // Implement search functionality
  }
}
