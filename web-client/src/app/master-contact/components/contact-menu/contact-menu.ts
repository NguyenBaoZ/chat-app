import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { CONTACT_MENU_CONSTANTS } from './contact-menu.constants';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { List } from '../../../shared/components/list/list';
import { filter, startWith } from 'rxjs';
import { Store } from '@ngrx/store';
import { ListActions } from '../../../state/list.actions';

@Component({
  selector: 'app-contact-menu',
  standalone: false,
  templateUrl: './contact-menu.html',
  styleUrl: './contact-menu.scss'
})
export class ContactMenu implements OnInit {
  public id = 'contact-menu';
  public items = CONTACT_MENU_CONSTANTS.DEFAULT_ITEMS;
  public selectedMenuItem: any;

  constructor(private router: Router, private route: ActivatedRoute, private store: Store) { }

  ngOnInit(): void {
    this.store.dispatch(ListActions.setItems({
      listId: this.id,
      items: this.items
    }))
    this.router.events.pipe(
      filter((e) => e instanceof NavigationEnd),
      startWith(this.router)).
      subscribe((event) => {
        const routes = event.url.toLowerCase().split('/');
        const currentRoute = routes[3];
        this.store.dispatch(ListActions.setSelectedItems({ listId: this.id, itemId: currentRoute ?? 'friend-list' }))
      });
  }

  onMenuItemSelected(item: any): void {
    this.router.navigate([item.id], { relativeTo: this.route });
  }
}
