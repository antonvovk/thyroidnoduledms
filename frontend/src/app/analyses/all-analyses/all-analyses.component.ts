import { Component, OnInit } from '@angular/core';
import { AnalysesService } from "../analyses.service";
import { Analysis } from "../../_models/analysis.model";
import { MatDialog } from "@angular/material/dialog";
import { AddEditAnalysisComponent } from "./add-edit-analisys/add-edit-analysis.component";
import { AuthService } from "../../auth/auth.service";
import { AnalysisPhotosComponent } from "./analysis-photos/analysis-photos.component";
import { PageEvent } from "@angular/material/paginator";

@Component({
  selector: 'app-all-analyses',
  templateUrl: './all-analyses.component.html',
  styleUrls: ['./all-analyses.component.scss']
})
export class AllAnalysesComponent implements OnInit {

  displayedColumns: string[] = [
    'index',
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
  totalItems: number = 0;
  pageIndex = 0
  pageSize = 10

  constructor(private analysesService: AnalysesService,
              public dialog: MatDialog,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.fetchAnalysis()
  }

  addAnalysis(): void {
    this.openAddEditDialog()
  }

  editAnalysis(element: Analysis): void {
    this.openAddEditDialog(element)
  }

  editPhotos(element: Analysis): void {
    const dialogRef = this.dialog.open(AnalysisPhotosComponent, {
      width: '80%',
      data: element
    });

    dialogRef.afterClosed().subscribe((analysis: Analysis) => {
      if (analysis != null) {
        console.log(analysis);
      }
    });
  }

  onPaginatorChange(event: PageEvent) {
    this.pageIndex = event.pageIndex
    this.pageSize = event.pageSize
    this.fetchAnalysis()
  }

  private fetchAnalysis() {
    this.analysesService.getAll(this.pageIndex, this.pageSize).subscribe(analyses => {
      this.analyses = analyses.elements
      this.totalItems = analyses.size
    })
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
          this.analysesService.update({...analysis, id: element.id}).subscribe((res) => {
            let indx = this.analyses.findIndex(a => a.id == res.id)
            this.analyses[indx] = res
            this.analyses = [...this.analyses]
          })
        } else {
          this.analysesService.create(analysis).subscribe((res) => {
            this.analyses.push(res)
            this.analyses = [...this.analyses]
          })
        }
      }
    });
  }
}
