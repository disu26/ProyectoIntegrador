import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ProjectFormComponent } from './forms/project-form/project-form.component';
import { RiskFormComponent } from './forms/risk-form/risk-form.component';
import { RiskTableComponent } from './risk-table/risk-table.component';
import { environment } from '../environments/environment';
import { AngularFireModule } from '@angular/fire/compat';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { RiskDetailComponent } from './risk-detail/risk-detail.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { HttpClientModule } from '@angular/common/http';
import { ProjectIdComponent } from './project-id/project-id.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { EditRiskComponent } from './forms/edit-risk/edit-risk.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NavbarComponent } from './navbar/navbar.component';
import { HistoryComponent } from './history/history.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { EditProjectComponent } from './forms/edit-project/edit-project.component';

@NgModule({
  declarations: [
    AppComponent,
    EditProjectComponent,
    EditRiskComponent,
    ProjectFormComponent,
    RiskFormComponent,
    HistoryComponent,
    NavbarComponent,
    ProjectDetailComponent,
    ProjectIdComponent,
    RiskDetailComponent,
    RiskTableComponent,
    WelcomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    FontAwesomeModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    BrowserAnimationsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    NgxChartsModule,
    NgxPaginationModule,
    ReactiveFormsModule,
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
