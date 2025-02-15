import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StartRoutingModule } from './start-routing.module';
import { StartComponent } from './start.component';
import { ShareModule } from "../shared/share.module";

@NgModule({
  declarations: [
    StartComponent,
  ],
  imports: [
    CommonModule,
    StartRoutingModule,
    ShareModule
  ]
})
export class StartModule {
}
