import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ChatMessage } from '../../../../models/chat-message.model';

@Component({
  selector: 'app-chat-bubble',
  standalone: false,
  templateUrl: './chat-bubble.html',
  styleUrl: './chat-bubble.scss'
})
export class ChatBubble {
  @Input() message!: ChatMessage;
  @Input() isOwnMessage: boolean = false;
  @Output() delete = new EventEmitter<string>();
  @Output() react = new EventEmitter<string>();

  availableReactions = ['👍', '❤️', '😂', '😮', '😢'];

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
}
