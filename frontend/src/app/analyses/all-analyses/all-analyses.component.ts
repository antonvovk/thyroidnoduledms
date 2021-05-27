import { Component, OnInit } from '@angular/core';
import { AnalysesService } from "../analyses.service";
import { Analysis } from "../../_models/analysis.model";
import { MatDialog } from "@angular/material/dialog";
import { AddEditAnalysisComponent } from "./add-edit-analisys/add-edit-analysis.component";
import { AuthService } from "../../auth/auth.service";

@Component({
  selector: 'app-all-analyses',
  templateUrl: './all-analyses.component.html',
  styleUrls: ['./all-analyses.component.scss']
})
export class AllAnalysesComponent implements OnInit {

  displayedColumns: string[] = [
    'sex',
    'age',
    'size',
    'hasConglomerate',
    'shape',
    'contours',
    // 'structure',
    'echogenicity',
    'vascularization',
    'elastography',
    'autoimmuneThyroiditis',
    'suspiciousLymphNodes',
    'thirads',
    // 'bethesdaLevel',
    'createdBy',
    'updatedBy',
    'actions'
  ];
  analyses: Analysis[] = [];

  constructor(private analysesService: AnalysesService,
              public dialog: MatDialog,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.analysesService.getAll().subscribe(analyses => {
      console.log(analyses);
      this.analyses = analyses
    })
  }

  addAnalysis(): void {
    this.openAddEditDialog()
  }

  editAnalysis(element: Analysis): void {
    this.openAddEditDialog(element)
  }

  private openAddEditDialog(element?: Analysis): void {
    const dialogRef = this.dialog.open(AddEditAnalysisComponent, {
      width: '600px',
      data: element
    });

    dialogRef.afterClosed().subscribe((analysis: Analysis) => {
      if (analysis != null) {
        const user = this.authService.user
        if (user) {
          analysis.createdBy = user
          analysis.updatedBy = user
        }

        if (element) {
          analysis.id = element.id
          this.analysesService.update(analysis).subscribe(() => {
          })
        } else {
          this.analysesService.create(analysis).subscribe(() => {
          })
        }
      }
    });
  }
}
