import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ChatMessage } from '../../../models/chat-message.model';

@Component({
  selector: 'app-conversation-window',
  standalone: false,
  templateUrl: './conversation-window.html',
  styleUrl: './conversation-window.scss'
})
export class ConversationWindow {
  @Input() messages: ChatMessage[] = [];
  @Input() currentUserId: string = '';
  @Output() messageDeleted = new EventEmitter<string>();
  @Output() reactionAdded = new EventEmitter<{ messageId: string, reaction: string }>();

  @ViewChild('scrollContainer') private scrollContainer!: ElementRef;

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  private scrollToBottom(): void {
    try {
      this.scrollContainer.nativeElement.scrollTop =
        this.scrollContainer.nativeElement.scrollHeight;
    } catch (err) { }
  }
}
