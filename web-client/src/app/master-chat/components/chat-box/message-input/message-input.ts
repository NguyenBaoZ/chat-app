import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-message-input',
  standalone: false,
  templateUrl: './message-input.html',
  styleUrl: './message-input.scss'
})
export class MessageInput {
  @Output() messageSent = new EventEmitter<string>();
  @Output() imageSelected = new EventEmitter<File>();
  @Output() fileSelected = new EventEmitter<File>();

  onMessageSent(message: any) {
    this.messageSent.emit(message);
  }

  onImageSelected(file: File) {
    this.imageSelected.emit(file);
  }

  onFileSelected(file: File) {
    this.fileSelected.emit(file);
  }
}
