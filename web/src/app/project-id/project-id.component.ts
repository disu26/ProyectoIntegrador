import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../models/project';
import { Risk } from '../models/risk';
import { ProjectService } from '../service/project.service';
import { faFolderPlus } from '@fortawesome/free-solid-svg-icons';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-project-id',
  templateUrl: './project-id.component.html',
  styleUrls: ['./project-id.component.css'],
})
export class ProjectIdComponent implements OnInit {
  project: Project | undefined;
  risks: Risk[] = [];
  id: string | undefined;
  faFolderPlus = faFolderPlus;
  faPenToSquare = faPenToSquare;

  projects: Project[] | undefined;

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.getProject(`${id}`);
  }

  getProject(id: string) {
    this.projectService.getProject(id).subscribe((data) => {
      this.project = data;
      this.risks = data.risks;
    });
  }
}
