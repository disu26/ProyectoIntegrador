import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HistoryComponent } from './history/history.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { ProjectIdComponent } from './project-id/project-id.component';
import { RiskDetailComponent } from './risk-detail/risk-detail.component';

const routes: Routes = [
  { path: 'project/:id', component: ProjectIdComponent},
  { path: 'projects', component: ProjectDetailComponent },
  { path: 'riskDetail/:projectId/:riskId', component: RiskDetailComponent },
  { path: 'history', component: HistoryComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'projects' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
