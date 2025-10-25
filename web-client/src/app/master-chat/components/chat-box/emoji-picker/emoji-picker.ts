import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Emoji } from '../../../../shared/models/emoji.model';
import { MatMenuTrigger } from '@angular/material/menu';
import { emojiList as EMOJI_LIST, reactionEmojis as REACTION_LIST } from '../../../../../assets/emoji/emoji';

@Component({
  selector: 'app-emoji-picker',
  standalone: false,
  templateUrl: './emoji-picker.html',
  styleUrl: './emoji-picker.scss'
})
export class EmojiPicker implements OnInit {
  @ViewChild(MatMenuTrigger) menuTrigger!: MatMenuTrigger;
  @ViewChild('searchInput') searchInput!: ElementRef;

  @Input()
  public set reactionOnly(reactionOnly: boolean) {
    this._reactionOnly = reactionOnly;
    this.emojiList = reactionOnly ? REACTION_LIST : EMOJI_LIST;
  }

  public get reactionOnly(): boolean {
    return this._reactionOnly;
  }

  emojiList: Emoji[] = [];
  _reactionOnly = false;

  @Output() emojiSelected = new EventEmitter<Emoji>();
  @Output() closed = new EventEmitter<void>();
  @Output() opened = new EventEmitter<void>();

  ngOnInit(): void {
    // console.log('EmojiPicker initialized with', this.emojiList, 'emojis.');
  }

  selectEmoji(emoji: Emoji): void {
    this.emojiSelected.emit(emoji);
    this.menuTrigger.closeMenu();
  }

  onMenuOpened(): void {
    this.opened.emit();
  }

  onMenuClosed(): void {
    this.closed.emit();
  }

  openMenu(): void {
    this.menuTrigger.openMenu();
  }

  closeMenu(): void {
    this.menuTrigger.closeMenu();
  }
}
