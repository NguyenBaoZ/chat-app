import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { AuthenticationModule } from './authentication/authentication-module';
import { UserProfileModule } from './user-profile/user-profile-module';
import { MasterChatModule } from './master-chat/master-chat-module';
import { HttpClient, provideHttpClient, withFetch } from '@angular/common/http';
import { TranslateModule, TranslateLoader, provideTranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { MasterPageModule } from './master-page/master-page-module';

export function HttpLoaderFactory() {
  return new TranslateHttpLoader();
}

@NgModule({
  declarations: [
    App
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthenticationModule,
    UserProfileModule,
    // MasterChatModule,
    MasterPageModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  providers: [
    provideHttpClient(withFetch()),
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideClientHydration(withEventReplay()),
    provideTranslateService({
      fallbackLang: 'en',
      lang: 'en'
    })
  ],
  bootstrap: [App]
})
export class AppModule { }
