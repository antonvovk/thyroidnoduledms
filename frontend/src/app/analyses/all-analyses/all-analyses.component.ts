import { Component, OnInit } from '@angular/core';
import { AnalysesService } from "../analyses.service";

@Component({
  selector: 'app-all-analyses',
  templateUrl: './all-analyses.component.html',
  styleUrls: ['./all-analyses.component.scss']
})
export class AllAnalysesComponent implements OnInit {

  constructor(private analysesService: AnalysesService) {
  }

  ngOnInit(): void {
    this.analysesService.getAll().subscribe(analyses => {
      console.log(analyses);
    })
  }
}
