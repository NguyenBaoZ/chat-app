import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-list-item',
  standalone: false,
  templateUrl: './list-item.html',
  styleUrl: './list-item.scss'
})
export class ListItem implements OnInit {
  @Input() item!: any;
  @Input() isSelected: boolean = false;
  @Input() buttons: any = [];

  @Output() itemSelected = new EventEmitter<any>();
  @Output() menuClicked = new EventEmitter<{ event: Event, item: any }>();
  @Output() editItem = new EventEmitter<any>();
  @Output() deleteItem = new EventEmitter<any>();

  ngOnInit(): void {
    console.log('itemlist',this.buttons)
  }

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
    for (let i = 0; i < seed?.length; i++) {
      hash = seed.charCodeAt(i) + ((hash << 5) - hash);
    }
    return colors[Math.abs(hash) % colors.length];
  }

  onItemClick(): void {
    this.itemSelected.emit(this.item);
  }

  onMenuClick(event: Event): void {
    event.stopPropagation();
    this.menuClicked.emit({ event, item: this.item });
  }

  onItemButtonClick(event: any) {
    if (!event?.onClick) return;
    event.onClick(this.item)
  }
}
