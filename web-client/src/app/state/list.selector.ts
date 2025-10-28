import { createSelector, createFeatureSelector } from '@ngrx/store';

export const getListState = createFeatureSelector<ReadonlyArray<any>>('list');