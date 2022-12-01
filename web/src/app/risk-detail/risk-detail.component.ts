import { ProjectService } from './../service/project.service';
import { Risk } from './../models/risk';
import { Project } from './../models/project';
import { ActivatedRoute } from '@angular/router';
import { RiskService } from './../service/risk.service';
import { Component, OnInit } from '@angular/core';
import { faHeartCirclePlus } from '@fortawesome/free-solid-svg-icons';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-risk-detail',
  templateUrl: './risk-detail.component.html',
  styleUrls: ['./risk-detail.component.css'],
})
export class RiskDetailComponent implements OnInit {
  faHeartCirclePlus = faHeartCirclePlus;
  project: Project | undefined;
  risks: Risk[] = [];
  projectId?: string | null;
  riskId: string | undefined;

  projects: Project[] | undefined;
  risk: Risk | undefined;

  faPenToSquare = faPenToSquare;

  constructor(
    private service: RiskService,
    private route: ActivatedRoute,
    private projectService: ProjectService
  ) {}

  ngOnInit(): void {
    const projectId = this.route.snapshot.paramMap.get('projectId');
    const riskId = this.route.snapshot.paramMap.get('riskId');
    this.projectId = projectId;
    this.getProject(`${projectId}`, `${riskId}`);
  }

  getProject(projactId: string, riskId: string) {
    this.projectService.getProject(projactId).subscribe((data) => {
      this.project = data;
      this.risks = data.risks;
      const risk = this.risks.filter((risk) => risk.id === riskId);
      this.risk = risk[0];
    });
  }
}

