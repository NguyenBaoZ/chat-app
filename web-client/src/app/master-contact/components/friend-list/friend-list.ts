import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { UsersService } from '../../../shared/services/users.servive';
import { ListActions } from '../../../state/list.actions';
import { CONTACT_MENU_CONSTANTS } from '../contact-menu/contact-menu.constants';
import { CONTACT_MENU_ITEM } from '../contact-menu/contact-menu.enum';

@Component({
  selector: 'app-friend-list',
  standalone: false,
  templateUrl: './friend-list.html',
  styleUrl: './friend-list.scss'
})
export class FriendList {
  public id = 'friend-list';
  public friends = [] as any;
  public selectedMenuItem: any;
  public headerItem: any;
  public itemButtons = [
    {
      displayName: 'Open Profile',
      onClick: (e: any) => {
        console.log('Open Profile', e)
      }
    },
    {
      displayName: 'Unfriend',
      onClick: (e: any) => {
        console.log('Unfriend', e)
      }
    }
  ] as any;

  constructor(private router: Router, private route: ActivatedRoute, private store: Store, private userService: UsersService) { }

  ngOnInit(): void {
    this.headerItem = CONTACT_MENU_CONSTANTS.DEFAULT_ITEMS[CONTACT_MENU_ITEM.FRIEND_LIST]
    this.userService.getContactsList().subscribe({
      next: (contacts: any) => {
        console.log('Friendlist get:', contacts);
        this.store.dispatch(ListActions.setItems({
          listId: this.id,
          items: contacts?.map((contact: any) => ({
            ...contact,
            id: contact?.contactId,
            displayName: contact?.nickname,
            usingImage: true
          })
          )
        }))
      }
    })
  }

  onMenuItemSelected(item: any): void {
    this.router.navigate([item.id], { relativeTo: this.route });
  }
}
