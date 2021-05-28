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
import { AddEditAnalysisComponent } from './all-analyses/add-edit-analisys/add-edit-analysis.component';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDialogModule } from "@angular/material/dialog";
import { MatInputModule } from "@angular/material/input";
import { MatRadioModule } from "@angular/material/radio";
import { MatSelectModule } from "@angular/material/select";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { ReactiveFormsModule } from "@angular/forms";
import { AnalysisPhotosComponent } from './all-analyses/analysis-photos/analysis-photos.component';
import { MatTooltipModule } from "@angular/material/tooltip";


@NgModule({
  declarations: [
    AllAnalysesComponent,
    AddEditAnalysisComponent,
    AnalysisPhotosComponent
  ],
    imports: [
        CommonModule,
        AnalysesRoutingModule,
        MatTableModule,
        MatPaginatorModule,
        MatButtonModule,
        MatIconModule,
        ShareModule,
        TranslateModule,
        MatFormFieldModule,
        MatDialogModule,
        MatInputModule,
        MatRadioModule,
        MatSelectModule,
        MatCheckboxModule,
        ReactiveFormsModule,
        MatTooltipModule
    ],
  providers: [
    AnalysesService
  ]
})
export class AnalysesModule {
}
