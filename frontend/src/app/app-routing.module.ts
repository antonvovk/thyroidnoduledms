import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'auth',
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'qualification',
    loadChildren: () => import('./qualification/qualification.module').then(m => m.QualificationModule)
  },
  {
    path: 'analyses',
    loadChildren: () => import('./analyses/analyses.module').then(m => m.AnalysesModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
