import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnalysesRoutingModule } from './analyses-routing.module';
import { AllAnalysesComponent } from './all-analyses/all-analyses.component';
import { AnalysesService } from "./analyses.service";


@NgModule({
  declarations: [
    AllAnalysesComponent
  ],
  imports: [
    CommonModule,
    AnalysesRoutingModule
  ],
  providers: [
    AnalysesService
  ]
})
export class AnalysesModule {
}
