import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ChatMessage } from '../../models/chat-message.model';

@Component({
  selector: 'app-chat-box',
  standalone: false,
  templateUrl: './chat-box.html',
  styleUrl: './chat-box.scss'
})
export class ChatBox {
  @Input() messages: ChatMessage[] = [{
    id: 'temp-id-' + Math.random().toString(36).substr(2, 9),
    text: 'HI',
    timestamp: new Date(),
    status: 'sent',
    isOwnMessage: true
  },
  {
    id: 'temp-id-' + Math.random().toString(36).substr(2, 9),
    text: 'Hello! How can I assist you today?',
    timestamp: new Date(),
    status: 'sent',
    isOwnMessage: false
  }];
  @Input() currentUserId: string = '';
  @Output() messageSent = new EventEmitter<string>();
  @Output() messageDeleted = new EventEmitter<string>();
  @Output() reactionAdded = new EventEmitter<{ messageId: string, reaction: string }>();

  onMessageSent(message: string) {
    console.log('Message sent from ChatBox:', message, this.messages);
    this.messages.push({
      id: 'temp-id-' + Math.random().toString(36).substr(2, 9),
      text: message,
      timestamp: new Date(),
      status: 'sent',
      isOwnMessage: true
    });
  }
}
