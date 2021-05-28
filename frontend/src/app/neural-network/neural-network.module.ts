import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NeuralNetworkRoutingModule } from './neural-network-routing.module';
import { NeuralNetworkComponent } from './neural-network/neural-network.component';
import { NeuralNetworkService } from "./neural-network.service";
import { MatFormFieldModule } from "@angular/material/form-field";
import { ReactiveFormsModule } from "@angular/forms";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatRadioModule } from "@angular/material/radio";
import { MatSelectModule } from "@angular/material/select";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { TranslateModule } from "@ngx-translate/core";
import { MatCardModule } from "@angular/material/card";
import { ShareModule } from "../shared/share.module";
import { MatButtonModule } from "@angular/material/button";

@NgModule({
  declarations: [
    NeuralNetworkComponent
  ],
  imports: [
    CommonModule,
    NeuralNetworkRoutingModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatIconModule,
    MatInputModule,
    MatRadioModule,
    MatSelectModule,
    MatCheckboxModule,
    TranslateModule,
    MatCardModule,
    ShareModule,
    MatButtonModule
  ],
  providers: [
    NeuralNetworkService
  ]
})
export class NeuralNetworkModule {
}
