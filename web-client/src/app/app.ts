import { Component, signal } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import * as en from '../assets/i18n/en.json'

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.scss'
})
export class App {
  title = signal('ktpm-web-ui');

  constructor(private translate: TranslateService) {
    translate.setTranslation('en', en);
    translate.setFallbackLang('en');
  }
}
