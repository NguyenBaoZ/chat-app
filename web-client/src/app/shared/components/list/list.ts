import { ChangeDetectorRef, Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { map, Observable, Subject, takeUntil } from 'rxjs';
import { getListState } from '../../../state/list.selector';
import { ListActions } from '../../../state/list.actions';

@Component({
  selector: 'app-list',
  standalone: false,
  templateUrl: './list.html',
  styleUrl: './list.scss'
})
export class List implements OnInit, OnDestroy {
  @Input() id: string = 'default-list';
  @Input() noHeader: boolean = false;
  @Input() title?: string;
  @Input() items: any[] = [];
  @Input() isLoading: boolean = false;
  @Input() disableSearch: boolean = false;
  @Input() disableSort: boolean = false;
  @Input() headerButtons: any = [];
  @Input() itemButtons: any = [];

  @Output() itemSelected = new EventEmitter<any>();

  constructor(private store: Store, private cdRef: ChangeDetectorRef) { }

  selectedItem$!: Observable<any>;
  items$!: Observable<any>;
  private destroy$ = new Subject<void>();

  @Input() selectedItem: any | null = null;
  contextMenuContact: any | null = null;

  ngOnInit() {
    console.log('list',this.itemButtons)
    this.items$! = this.store.select(getListState).pipe(
      map((res: any) => res[this.id]?.items),
      takeUntil(this.destroy$)
    )
    this.selectedItem$ = this.store.select(getListState).pipe(
      map((res: any) => res[this.id]?.selectedItemId),
      takeUntil(this.destroy$)
    )

    this.items$.subscribe({
      next: (res: any) => {
        console.log(this.id, 'items', res);
        this.items = res;
        this.cdRef.detectChanges();
      }
    })
    this.selectedItem$.subscribe({
      next: (res: any) => {
        console.log(this.id, 'current', res);
        this.selectedItem = this.items?.find(item => item?.id == res)
      }
    });
  }

  onItemSelected(item: any): void {
    this.selectedItem = item;
    this.itemSelected.emit(item);
  }

  onMenuClicked(data: { event: Event, item: any }): void {
    this.contextMenuContact = data.item;
    // You could open a context menu here if needed
  }

  searchItems(): void {
    console.log('Search items');
    // Implement search functionality
  }

  sortItems(): void {
    console.log('Sort items');
    // Implement sort functionality
  }

  onEditItem(event: any) { }

  onDeleteItem(event: any) { }

  setSelectedItem(item: any) {
    this.selectedItem = item;
    console.log(this.selectedItem)
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

