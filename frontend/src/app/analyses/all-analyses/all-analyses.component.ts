import { Component, OnInit } from '@angular/core';
import { AnalysesService } from "../analyses.service";
import { Analysis } from "../../_models/analysis.model";

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
    'updatedBy'
  ];
  analyses: Analysis[] = [];

  constructor(private analysesService: AnalysesService) {
  }

  ngOnInit(): void {
    this.analysesService.getAll().subscribe(analyses => {
      console.log(analyses);
      this.analyses = analyses
    })
  }
}
