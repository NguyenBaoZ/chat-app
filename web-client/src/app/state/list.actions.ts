// app/store/menu/menu.actions.ts
import { createAction, createActionGroup, props } from '@ngrx/store';

export const ListActions = createActionGroup({
  source: 'AppList',
  events: {
    'Set Selected Items': props<{ listId: string, itemId: string }>(),
    'Set Items': props<{ listId: string, items: any[] }>(),
    'Clear List': props<{ listId: string }>()
  },
});