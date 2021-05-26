import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllAnalysesComponent } from "./all-analyses/all-analyses.component";

const routes: Routes = [
  {
    path: '',
    component: AllAnalysesComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AnalysesRoutingModule {
}
