import { ProjectSave } from './../../models/projectSave';

import { ProjectService } from './../../service/project.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css'],
})
export class ProjectFormComponent implements OnInit {
  @Input() show = false;
  project: ProjectSave = this.getEmptyProject();
  tags?: string;
  emails?: string;
  errorMessage: boolean = false;

  constructor(private service: ProjectService) {}

  ngOnInit(): void {
    this.errorMessage = false;
  }

  onSubmit() {
    this.project = this.getEmptyProject();
  }

  saveProject() {
    this.project.status = 'Creado';
    if (this.tags != undefined) {
      this.project.labels = this.removeSpaces(this.tags.split(','));
    }
    if (this.emails != undefined) {
      this.project.emails = this.removeSpaces(this.emails.split(','));
    }

    let validLengthTag = this.validateLengthTag(this.project.labels);
    let validEmails = this.validateEmails(this.project.emails);
    let validEmptyFields = this.validateEmptyFields();
    if (validLengthTag && validEmails && validEmptyFields) {
      this.service.saveProject(this.project).subscribe(() => {
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      });
    } else {
      this.errorMessage = true;
    }
  }

  getEmptyProject(): ProjectSave {
    return {
      name: '',
      labels: [],
      emails: [],
      description: '',
    };
  }

  validateEmails(emailList: string[]) {
    for (let index = 0; index < emailList.length; index++) {
      let validate = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(
        emailList[index]
      );
      if (validate == false) {
        return false;
      }
    }
    return true;
  }

  validateLengthTag(array: string[]) {
    for (let index = 0; index < array.length; index++) {
      let lengthTag = array[0].length;
      if (lengthTag > 50) {
        return false;
      }
    }
    return true;
  }

  closeModal() {
    this.show = false;
    this.project = this.getEmptyProject();
    this.errorMessage = false;
  }

  validateEmptyFields() {
    if (
      this.project.name !== '' &&
      this.project.startDate != undefined &&
      this.project.description !== ''
    ) {
      return true;
    }
    return false;
  }

  removeSpaces(array: string[]) {
    return array.map((i) => i.trim());
  }
}
