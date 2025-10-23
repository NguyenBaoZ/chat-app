import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Emoji } from '../../../../models/emoji.model';
import { EmojiPicker } from '../../emoji-picker/emoji-picker';

@Component({
  selector: 'app-main-input',
  standalone: false,
  templateUrl: './main-input.html',
  styleUrl: './main-input.scss'
})
export class MainInput implements OnInit, AfterViewInit {
  @ViewChild(EmojiPicker) emojiPicker!: EmojiPicker;
  @Input() message: string = '';
  @Output() messageChange = new EventEmitter<string>();
  @Output() sendMessage = new EventEmitter<string>();

  @ViewChild('messageTextarea') textarea!: ElementRef<HTMLTextAreaElement>;

  showEmojiPicker = false;

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.autoResize();
    this.emojiPicker?.closeMenu();
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

  addEmoji(emoji: Emoji) {
    this.insertEmojiInTextarea(emoji.emoji);
    this.focusTextarea();
  }

  private insertEmojiInTextarea(emoji: string): void {
    const textarea = this.textarea.nativeElement;

    const cursorPosition = textarea.selectionStart !== null ? textarea.selectionStart : this.message.length;

    const textBeforeCursor = this.message.substring(0, cursorPosition);
    const textAfterCursor = this.message.substring(cursorPosition);

    this.message = textBeforeCursor + emoji + textAfterCursor;

    setTimeout(() => {
      textarea.focus();
      const newCursorPosition = cursorPosition + emoji.length;
      textarea.setSelectionRange(newCursorPosition, newCursorPosition);
    });

    this.messageChange.emit(this.message);
    this.autoResize();
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
}
