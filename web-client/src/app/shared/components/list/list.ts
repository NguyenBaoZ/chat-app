import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list',
  standalone: false,
  templateUrl: './list.html',
  styleUrl: './list.scss'
})
export class List {
  @Input() title?: string;
  @Input() items: any[] = [];
  @Input() isLoading: boolean = false;

  @Output() itemSelected = new EventEmitter<any>();

  constructor(private router: Router, private route: ActivatedRoute) { }


  @Input() selectedItem: any | null = null;
  contextMenuContact: any | null = null;

  onItemSelected(item: any): void {
    this.selectedItem = item;
    this.itemSelected.emit(item);
  }

  onMenuClicked(data: { event: Event, item: any }): void {
    this.contextMenuContact = data.item;
    // You could open a context menu here if needed
  }

  addNewContact(): void {
    console.log('Add new contact');
    // Implement add functionality
  }

  searchContacts(): void {
    console.log('Search contacts');
    // Implement search functionality
  }

  onEditItem(event: any) { }

  onDeleteItem(event: any) { }

  setSelectedItem(item: any) {
    this.selectedItem = item;
    console.log(this.selectedItem)
  }
}
