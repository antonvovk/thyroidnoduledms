import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QualificationTestedGuard } from "./_guards/qualification-tested.guard";
import { AuthGuard } from "./_guards/auth.guard";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    loadChildren: () => import('./start/start.module').then(m => m.StartModule)
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
    loadChildren: () => import('./analyses/analyses.module').then(m => m.AnalysesModule),
    canLoad: [AuthGuard, QualificationTestedGuard]
  },
  {
    path: 'account',
    loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
    canLoad: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
