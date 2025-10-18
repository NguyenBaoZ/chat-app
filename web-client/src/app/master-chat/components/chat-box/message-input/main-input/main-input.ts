import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-main-input',
  standalone: false,
  templateUrl: './main-input.html',
  styleUrl: './main-input.scss'
})
export class MainInput implements OnInit, AfterViewInit {
  @Input() message: string = '';
  @Output() messageChange = new EventEmitter<string>();
  @Output() sendMessage = new EventEmitter<string>();

  @ViewChild('messageTextarea') textarea!: ElementRef<HTMLTextAreaElement>;

  showEmojiPicker = false;
  availableEmojis = ['😀', '😂', '❤️', '👍', '🎉', '🔥'];

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.autoResize();
  }

  onMessageChange(value: string) {
    this.message = value;
    this.messageChange.emit(value);
    this.autoResize();
  }

  onSendMessage() {
    if (this.message.trim()) {
      this.sendMessage.emit(this.message.trim());
      this.showEmojiPicker = false;
      this.resetTextareaHeight();
      this.message = '';
    }
  }

  addEmoji(emoji: string) {
    this.onMessageChange(this.message + emoji);
    this.focusTextarea();
  }

  onKeyPress(event: KeyboardEvent) {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      this.onSendMessage();
    }
  }

  private autoResize() {
    if (this.textarea?.nativeElement) {
      const textarea = this.textarea.nativeElement;
      textarea.style.height = 'auto';
      textarea.style.height = Math.min(textarea.scrollHeight, 120) + 'px';
    }
  }

  private resetTextareaHeight() {
    if (this.textarea?.nativeElement) {
      this.textarea.nativeElement.style.height = 'auto';
    }
  }

  private focusTextarea() {
    if (this.textarea?.nativeElement) {
      this.textarea.nativeElement.focus();
    }
  }

  toggleEmojiPicker() {
    this.showEmojiPicker = !this.showEmojiPicker;
    if (!this.showEmojiPicker) {
      this.focusTextarea();
    }
  }
}
