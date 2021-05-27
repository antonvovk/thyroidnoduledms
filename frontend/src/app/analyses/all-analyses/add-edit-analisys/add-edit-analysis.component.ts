import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
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
} from "../../../_models/enums";
import { Analysis } from "../../../_models/analysis.model";

@Component({
  selector: 'app-add-edit-analysis',
  templateUrl: './add-edit-analysis.component.html',
  styleUrls: ['./add-edit-analysis.component.scss']
})
export class AddEditAnalysisComponent implements OnInit {

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
      bethesdaLevel: new FormControl(null, [Validators.required])
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

  editMode = true;

  constructor(
    private dialogRef: MatDialogRef<AddEditAnalysisComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Analysis) {

    if (data == null) {
      this.editMode = false
    } else {
      this.form.patchValue(data)
    }
  }

  getControl(group: string, control: string): FormControl {
    return this.form.get(group)?.get(control) as FormControl
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onYesClick() {
    if (this.form.invalid) {
      this.form.markAsTouched()
      return
    }

    this.dialogRef.close(this.form.value as Analysis)
  }
}
