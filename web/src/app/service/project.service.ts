import { ProjectSave } from './../models/projectSave';
import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private url = 'https://matriz-riesgos.herokuapp.com/';

  constructor(private http: HttpClient) {}

  getProjects(): Observable<Project[]> {
    let direction = this.url + 'getAllProjects';
    return this.http.get<Project[]>(direction);
  }

  getProject(id: string): Observable<Project> {
    let direction = this.url + 'getProjectById/' + id;
    return this.http.get<Project>(direction);
  }

  updateProject(project: ProjectSave): Observable<any> {
    let direction = this.url + 'updateProject';
    return this.http.put<any>(direction, project, {
      responseType: 'text' as 'json',
    });
  }

  saveProject(project: ProjectSave): Observable<ProjectSave> {
    let direction = this.url + 'createProject';
    return this.http.post<ProjectSave>(direction, project, {
      responseType: 'text' as 'json',
    });
  }

  deleteProject(id: string): Observable<Project> {
    let direction = this.url + 'deleteProject/' + id;
    return this.http.delete<Project>(direction);
  }
}
