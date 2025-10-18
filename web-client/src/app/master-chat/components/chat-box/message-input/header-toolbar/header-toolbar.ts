import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-header-toolbar',
  standalone: false,
  templateUrl: './header-toolbar.html',
  styleUrl: './header-toolbar.scss'
})
export class HeaderToolbar {
  @Output() imageSelected = new EventEmitter<File>();
  @Output() fileSelected = new EventEmitter<File>();

  openImageBrowser() {
    const input = document.createElement('input');
    input.type = 'file';
    input.accept = 'image/*';
    input.onchange = (event: any) => {
      const file = event.target.files[0];
      if (file) this.imageSelected.emit(file);
    };
    input.click();
  }

  openFileBrowser() {
    const input = document.createElement('input');
    input.type = 'file';
    input.onchange = (event: any) => {
      const file = event.target.files[0];
      if (file) this.fileSelected.emit(file);
    };
    input.click();
  }
}
