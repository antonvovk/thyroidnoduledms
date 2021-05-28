import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { Analysis } from "../../../_models/analysis.model";
import { AnalysesService } from "../../analyses.service";
import { UltrasoundImage } from "../../../_models/ultrasound-image.model";
import { environment } from "../../../../environments/environment";

@Component({
  selector: 'app-analysis-photos',
  templateUrl: './analysis-photos.component.html',
  styleUrls: ['./analysis-photos.component.scss']
})
export class AnalysisPhotosComponent {

  constructor(
    private analysisService: AnalysesService,
    private dialogRef: MatDialogRef<AnalysisPhotosComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Analysis) {
  }

  onFileSelected(event: Event) {
    // @ts-ignore
    const file: File = event.target.files[0];

    if (file) {
      const image = <UltrasoundImage>{
        filename: file.name,
        height: 0,
        width: 0,
        id: 0
      }

      this.analysisService.postFile(file).subscribe(() => {
        this.analysisService.addImage(this.data.id, image).subscribe((id) => {
          image.id = id
          this.data.ultrasoundAnalysis.images.push(image)
        });
      });
    }
  }

  onYesClick(): void {
    this.dialogRef.close();
  }

  getImageUrl(filename: string) {
    return `${environment.apiUrl}/files/${filename}`
  }

  removeImage(image: UltrasoundImage) {
    this.analysisService.removeImage(this.data.id, image).subscribe(() => {
      this.data.ultrasoundAnalysis.images = this.data.ultrasoundAnalysis.images.filter(x => x.id !== image.id);
    });
  }

  trackById(index: number, item: Analysis): number {
    return item.id
  }
}
