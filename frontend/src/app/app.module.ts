import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from "@angular/common/http";
import { AuthInterceptor } from "./_interceptors/auth.interceptor";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { TranslateLoader, TranslateModule } from "@ngx-translate/core";
import { ErrorInterceptor } from "./_interceptors/error.interceptor";
import { ToastrModule } from "ngx-toastr";
import { QualificationTestedGuard } from "./_guards/qualification-tested.guard";
import { AuthGuard } from "./_guards/auth.guard";
import { MatPaginatorIntl } from "@angular/material/paginator";

import localeFr from '@angular/common/locales/uk';
import { registerLocaleData } from "@angular/common";

registerLocaleData(localeFr);

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

const dutchRangeLabel = (page: number, pageSize: number, length: number) => {
  if (length == 0 || pageSize == 0) {
    return `0 до ${length}`;
  }

  length = Math.max(length, 0);

  const startIndex = page * pageSize;

  // If the start index exceeds the list length, do not try and fix the end index to the end.
  const endIndex = startIndex < length ?
    Math.min(startIndex + pageSize, length) :
    startIndex + pageSize;

  return `${startIndex + 1} - ${endIndex} до ${length}`;
}

export function getDutchPaginatorIntl() {
  const paginatorIntl = new MatPaginatorIntl();

  paginatorIntl.itemsPerPageLabel = 'Елементів на сторінку:';
  paginatorIntl.nextPageLabel = 'Наступна сторінка';
  paginatorIntl.previousPageLabel = 'Попрередня сторінка';
  paginatorIntl.getRangeLabel = dutchRangeLabel;
  paginatorIntl.lastPageLabel = 'Остання сторінка'
  paginatorIntl.firstPageLabel = 'Перша сторінка'

  return paginatorIntl;
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      defaultLanguage: 'ua',
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true,
    },
    AuthGuard,
    QualificationTestedGuard,
    {provide: MatPaginatorIntl, useValue: getDutchPaginatorIntl()},
    {
      provide: LOCALE_ID,
      useValue: 'uk-UA'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
