import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QualificationTestingComponent } from "./qualification-testing/qualification-testing.component";

const routes: Routes = [
  {
    path: 'testing',
    component: QualificationTestingComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QualificationRoutingModule {
}
