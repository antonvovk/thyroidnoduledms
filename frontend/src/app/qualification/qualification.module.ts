import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QualificationRoutingModule } from './qualification-routing.module';
import { QualificationTestingComponent } from './qualification-testing/qualification-testing.component';
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { ReactiveFormsModule } from "@angular/forms";
import { MatInputModule } from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { QualificationService } from "./qualification.service";

@NgModule({
  declarations: [
    QualificationTestingComponent
  ],
  imports: [
    CommonModule,
    QualificationRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [
    QualificationService,
  ]
})
export class QualificationModule {
}
