import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { MatIconModule } from "@angular/material/icon";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatButtonModule } from "@angular/material/button";
import { RouterModule } from "@angular/router";
import { MatMenuModule } from "@angular/material/menu";

@NgModule({
  declarations: [
    HeaderComponent
  ],
    imports: [
        CommonModule,
        MatIconModule,
        MatToolbarModule,
        MatButtonModule,
        RouterModule,
      MatMenuModule
    ],
  exports: [
    HeaderComponent
  ]
})
export class ShareModule {
}
