import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NeuralNetworkComponent } from "./neural-network/neural-network.component";

const routes: Routes = [
  {
    path: '',
    component: NeuralNetworkComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NeuralNetworkRoutingModule { }
