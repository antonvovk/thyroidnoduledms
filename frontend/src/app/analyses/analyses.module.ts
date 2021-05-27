import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnalysesRoutingModule } from './analyses-routing.module';
import { AllAnalysesComponent } from './all-analyses/all-analyses.component';
import { AnalysesService } from "./analyses.service";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { ShareModule } from "../shared/share.module";
import { TranslateModule } from "@ngx-translate/core";


@NgModule({
  declarations: [
    AllAnalysesComponent
  ],
    imports: [
        CommonModule,
        AnalysesRoutingModule,
        MatTableModule,
        MatPaginatorModule,
        MatButtonModule,
        MatIconModule,
        ShareModule,
        TranslateModule
    ],
  providers: [
    AnalysesService
  ]
})
export class AnalysesModule {
}
