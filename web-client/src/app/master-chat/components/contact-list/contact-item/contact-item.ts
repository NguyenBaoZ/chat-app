import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Contact } from '../../../models/contact.model';

@Component({
  selector: 'app-contact-item',
  standalone: false,
  templateUrl: './contact-item.html',
  styleUrl: './contact-item.scss'
})
export class ContactItem {
  @Input() contact!: Contact;
  @Input() isSelected: boolean = false;

  @Output() contactSelected = new EventEmitter<Contact>();
  @Output() menuClicked = new EventEmitter<{ event: Event, contact: Contact }>();
  @Output() editContact = new EventEmitter<Contact>();
  @Output() deleteContact = new EventEmitter<Contact>();

  getInitials(name: string): string {
    return name
      .split(' ')
      .map(part => part[0])
      .join('')
      .toUpperCase()
      .substring(0, 2);
  }

  getRandomColor(seed: string): string {
    const colors = [
      '#f44336', '#e91e63', '#9c27b0', '#673ab7', '#3f51b5',
      '#2196f3', '#03a9f4', '#00bcd4', '#009688', '#4caf50',
      '#8bc34a', '#cddc39', '#ffeb3b', '#ffc107', '#ff9800'
    ];

    // Generate a consistent color based on the name
    let hash = 0;
    for (let i = 0; i < seed.length; i++) {
      hash = seed.charCodeAt(i) + ((hash << 5) - hash);
    }
    return colors[Math.abs(hash) % colors.length];
  }

  onItemClick(): void {
    this.contactSelected.emit(this.contact);
  }

  onMenuClick(event: Event): void {
    event.stopPropagation();
    this.menuClicked.emit({ event, contact: this.contact });
  }

  onEdit(): void {
    this.editContact.emit(this.contact);
  }

  onDelete(): void {
    this.deleteContact.emit(this.contact);
  }
}
