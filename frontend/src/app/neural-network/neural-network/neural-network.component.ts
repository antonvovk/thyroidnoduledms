import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import {
  BethesdaLevel,
  NoduleContours,
  NoduleEchogenicity,
  NoduleElastography,
  NoduleShape,
  NoduleSize,
  NoduleStructure,
  NoduleVascularization,
  Thirads
} from "../../_models/enums";
import { Analysis } from "../../_models/analysis.model";
import { NeuralNetworkService } from "../neural-network.service";
import { AuthService } from "../../auth/auth.service";

@Component({
  selector: 'app-neural-network',
  templateUrl: './neural-network.component.html',
  styleUrls: ['./neural-network.component.scss']
})
export class NeuralNetworkComponent implements OnInit {

  readonly form = new FormGroup({
    patientInfo: new FormGroup({
      sex: new FormControl(null, [Validators.required]),
      age: new FormControl(null, [Validators.required])
    }),
    ultrasoundAnalysis: new FormGroup({
      size: new FormControl(null, [Validators.required]),
      hasConglomerate: new FormControl(false, [Validators.required]),
      shape: new FormControl(null, [Validators.required]),
      contours: new FormControl(null, [Validators.required]),
      echogenicity: new FormControl(null, [Validators.required]),
      vascularization: new FormControl(null, [Validators.required]),
      elastography: new FormControl(null, [Validators.required]),
      autoimmuneThyroiditis: new FormControl(false, [Validators.required]),
      suspiciousLymphNodes: new FormControl(false, [Validators.required]),
      thirads: new FormControl(null, [Validators.required]),
      structure: new FormControl(null, [Validators.required])
    }),
    biopsyAnalysis: new FormGroup({
      bethesdaLevel: new FormControl(BethesdaLevel.CLASS6, [Validators.required])
    })
  })

  readonly sizes = Object.keys(NoduleSize)
  readonly shapes = Object.keys(NoduleShape)
  readonly contours = Object.keys(NoduleContours)
  readonly echogenicities = Object.keys(NoduleEchogenicity)
  readonly vascularizations = Object.keys(NoduleVascularization)
  readonly elastographies = Object.keys(NoduleElastography)
  readonly thirads = Object.keys(Thirads)
  readonly structures = Object.keys(NoduleStructure)
  readonly bethesdaLevels = Object.keys(BethesdaLevel)

  result: number | null = null

  constructor(private neuralNetworkService: NeuralNetworkService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  getControl(group: string, control: string): FormControl {
    return this.form.get(group)?.get(control) as FormControl
  }

  onYesClick() {
    if (this.form.invalid) {
      Object.values(this.form.controls).forEach(c => c.markAsTouched())
      return
    }

    const analysis = this.form.value as Analysis
    analysis.createdBy = this.authService.user
    analysis.updatedBy = this.authService.user

    this.neuralNetworkService.predict(analysis).subscribe(res => {
      this.result = <number><unknown>res
    })
  }
}
