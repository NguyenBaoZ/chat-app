import { createReducer, on } from '@ngrx/store';

import { ListActions } from './list.actions';

export const initialState: any = {};

export const listReducer = createReducer(
  initialState,
  on(ListActions.setSelectedItems, (_state, { listId, itemId }) => {
    const currentListState = Object.keys(_state).includes(listId) ? _state[listId] : {};
    return {
      ..._state,
      [listId]: {
        ...currentListState,
        selectedItemId: itemId
      }
    }
  }),
  on(ListActions.setItems, (_state, { listId, items }) => {
    const currentListState = Object.keys(_state).includes(listId) ? _state[listId] : {};
    return {
      ..._state,
      [listId]: {
        ...currentListState,
        items: items
      }
    }
  }),
  on(ListActions.clearList, (_state, { listId }) => {
    return Object.keys(_state).includes(listId) ? {
      ..._state,
      [listId]: {}
    } : _state
  })
);