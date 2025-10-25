import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ChatMessage } from '../../../../../shared/models/chat-message.model';
import { EmojiPicker } from '../../emoji-picker/emoji-picker';
import { Emoji } from '../../../../../shared/models/emoji.model';
import { reactionEmojis as REACTIONS } from '../../../../../../assets/emoji/emoji';

@Component({
  selector: 'app-chat-bubble',
  standalone: false,
  templateUrl: './chat-bubble.html',
  styleUrl: './chat-bubble.scss'
})
export class ChatBubble implements OnInit{
  @ViewChild(EmojiPicker) emojiPicker!: EmojiPicker;
  @Input() message!: ChatMessage;
  @Input() isOwnMessage: boolean = false;
  @Output() delete = new EventEmitter<string>();
  @Output() react = new EventEmitter<string>();

  public reactionList: Emoji[] = REACTIONS;
  public reaction?: string;
  showEmojiPicker = false;

  ngOnInit(): void {
    if (this.message.reaction) {
      this.reaction = this.message.reaction;
    }
  }

  getStatusIcon(status: string): string {
    switch (status) {
      case 'sending': return 'schedule';
      case 'sent': return 'done';
      case 'seen': return 'done_all';
      case 'error': return 'error';
      default: return 'schedule';
    }
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'sending': return 'accent';
      case 'sent': return 'primary';
      case 'seen': return 'primary';
      case 'error': return 'warn';
      default: return 'accent';
    }
  }

  toggleEmojiPicker() {
    if (this.showEmojiPicker) {
      this.showEmojiPicker = false;
      this.emojiPicker?.closeMenu();
    }
    else {
      this.showEmojiPicker = true;

      this.emojiPicker?.openMenu();
    }
  }

  onEmojiPickerClosed() {
    this.showEmojiPicker = false;
  }

  addEmoji(emoji: any) {
    this.reaction = emoji.emoji;
    this.react.emit(emoji);
  }
}
