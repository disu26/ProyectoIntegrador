import { ProjectService } from './../../service/project.service';
import { Component, OnInit, Input } from '@angular/core';
import { ProjectSave } from 'src/app/models/projectSave';

@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css'],
})
export class EditProjectComponent implements OnInit {
  @Input() project?: ProjectSave;
  @Input() show = false;
  projectEdit?: ProjectSave;
  tags?: string;
  emails?: string;
  errorMessage: boolean = false;

  constructor(private service: ProjectService) {}

  ngOnInit(): void {
    console.log(this.emails), this.tags;
    this.errorMessage = false;
  }

  ngOnChanges() {
    this.getvalues();
    this.errorMessage = false;
  }

  editProject() {
    if (this.tags != undefined && this.project != undefined) {
      this.project.labels = this.removeSpaces(this.tags.split(','));
    }

    if (this.emails != undefined && this.project != undefined) {
      this.project.emails = this.removeSpaces(this.emails.split(','));
    }

    this.project = this.getProyectEdit();
    if (this.project !== undefined) {
      let validLengthTag = this.validateLengthTag(this.project.labels);
      let validEmails = this.validateEmails(this.project.emails);

      if (validLengthTag && validEmails) {
        this.service.updateProject(this.project).subscribe(() => {
          setTimeout(() => {
            window.location.reload();
          }, 1000);
        });
        return;
      }
      this.errorMessage = true;
    }
  }

  getvalues() {
    this.tags = this.project?.labels.join(',');
    console.log('3' + this.tags);
    console.log('4' + this.project?.labels);

    this.emails = this.project?.emails.join(',');
    console.log('1' + this.emails);
    console.log('2' + this.project?.emails);
  }

  closeModal() {
    this.show = false;
    this.getvalues();
    this.errorMessage = false;
  }

  getProyectEdit() {
    if (this.project != undefined) {
      return {
        id: this.project.id,
        name: this.project?.name,
        startDate: this.project.startDate,
        endingDate: this.project.endingDate,
        labels: this.project.labels,
        emails: this.project.emails,
        description: this.project.description,
        status: this.project.status,
      };
    }
    return undefined;
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

  removeSpaces(array: string[]) {
    return array.map((i) => i.trim());
  }
}
