import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { Analysis } from "../../../_models/analysis.model";

@Component({
  selector: 'app-analysis-details',
  templateUrl: './analysis-details.component.html',
  styleUrls: ['./analysis-details.component.scss']
})
export class AnalysisDetailsComponent {

  constructor(
    private dialogRef: MatDialogRef<AnalysisDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Analysis) {
  }

  closeClick(): void {
    this.dialogRef.close()
  }
}
