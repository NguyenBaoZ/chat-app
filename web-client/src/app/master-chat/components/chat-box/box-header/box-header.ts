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
    displayName: 'NA',
    usingImage: true
  }
}
