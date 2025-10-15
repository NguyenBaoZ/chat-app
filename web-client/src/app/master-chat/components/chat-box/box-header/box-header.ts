import { Component } from '@angular/core';

@Component({
  selector: 'app-box-header',
  standalone: false,
  templateUrl: './box-header.html',
  styleUrl: './box-header.scss'
})
export class BoxHeader {
  public contact = {
    id: '1',
    name: 'NA',
    // avatarUrl: 'https://example.com/avatar1.png',
    email: ''
  }
}
