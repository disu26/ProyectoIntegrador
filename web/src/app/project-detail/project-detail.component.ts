import { Component, OnInit, ViewChild } from '@angular/core';
import { Project } from '../models/project';
import { FireserviceService } from '../service/fireservice.service';
import { ProjectService } from '../service/project.service';
import {
  faEye,
  faFolderPlus,
  faArrowUpRightFromSquare,
  faTrashCan,
} from '@fortawesome/free-solid-svg-icons';
import { DatePipe } from '@angular/common';
import { PrimeNGConfig } from 'primeng/api';
import { Table } from 'primeng/table';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.scss'],
  providers: [DatePipe],
})
export class ProjectDetailComponent implements OnInit {
  @ViewChild('dt') table?: Table;

  selectedProjects: Project[] = [];

  faEye = faEye;
  faFolderPlus = faFolderPlus;
  faArrowUpRightFromSquare = faArrowUpRightFromSquare;
  faTrashCan = faTrashCan;
  faPenToSquare = faPenToSquare;

  userLogged = this.afAuth.getUserLogged();
  uid: any;
  disabled: boolean = false;

  projects: Project[] = [];
  user: any = '';

  loading: boolean = true;

  constructor(
    private afAuth: FireserviceService,
    public projectService: ProjectService,
    private primengConfig: PrimeNGConfig
  ) {}

  ngOnInit(): void {
    this.getProjects();
    this.traerdatos();
    this.primengConfig.ripple = true;
  }

  getProjects() {
    this.userLogged.subscribe((value) => {
      this.uid = value?.uid;
    });
    this.projectService.getProjects().subscribe((data) => {
      this.projects = data;
    });
  }

  traerdatos() {
    this.userLogged.subscribe((value) => {
      if (value?.email == undefined) {
        this.disabled = true;
      } else {
        this.disabled = false;
      }
    });
  }

  onStartDateSelect(value: any) {
    if (this.table !== undefined) {
      this.table.filter(this.formatDate(value), 'startDate', 'equals');
    }
  }

  onEndingDateSelect(value: any) {
    if (this.table !== undefined) {
      this.table.filter(this.formatDate(value), 'endingDate', 'equals');
    }
  }

  formatDate(date: any) {
    let month = date.getMonth() + 1;
    let day = date.getDate();

    return date.getFullYear() + ',' + month + ',' + day;
  }

  deleteProject(id: string) {
    this.projectService.deleteProject(id).subscribe(() => {
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    });
  }
}

