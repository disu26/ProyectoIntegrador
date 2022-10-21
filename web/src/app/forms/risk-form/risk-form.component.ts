import { RiskService } from './../../service/risk.service';
import { Risk } from './../../models/risk';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-risk-form',
  templateUrl: './risk-form.component.html',
  styleUrls: ['./risk-form.component.css'],
})
export class RiskFormComponent implements OnInit {
  @Input() projectId: string | undefined;
  @Input() show = false;
  risk: Risk = this.getEmptyRisk();
  occurrence?: string;
  impactValue?: string;
  tags?: string;
  contingenceMails?: string;
  mitigationMails?: string;
  errorMessage: boolean = false;
  criticidad?: number;

  constructor(private service: RiskService) {}

  ngOnInit(): void {
    this.errorMessage = false;
    this.criticidad = 6;
  }

  saveRisk() {
    this.risk.projectId = this.projectId;
    if (this.occurrence != undefined) {
      this.risk.probability = Number(this.occurrence);
    }
    if (this.impactValue != undefined) {
      this.risk.impactValue = Number(this.impactValue);
    }
    if (this.tags != undefined) {
      this.risk.labels = this.removeSpaces(this.tags.split(','));
    }
    if (this.contingenceMails != undefined) {
      this.risk.responsibleContingencyMails = this.removeSpaces(
        this.contingenceMails.split(',')
      );
    }
    if (this.mitigationMails != undefined) {
      this.risk.responsibleMitigationMails = this.removeSpaces(
        this.mitigationMails.split(',')
      );
    }
    this.risk.userId = '11298';
    let validEmails =
      this.validateEmails(this.risk.responsibleContingencyMails) &&
      this.validateEmails(this.risk.responsibleMitigationMails);
    let validEmptyFields = this.validateEmptyFields();
    let validLengthTag = this.validateLengthTag(this.risk.labels);

    if (validEmails && validEmptyFields && validLengthTag) {
      this.service.saveRisk(this.risk).subscribe(() => {
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      });
      this.impactValue = '';
      this.occurrence = '';
    } else {
      this.errorMessage = true;
    }
  }

  onSubmit() {
    this.risk = this.getEmptyRisk();
  }

  getEmptyRisk(): Risk {
    return {
      projectId: '',
      name: '',
      userId: '',
      labels: [],
      description: '',
      riskState: '',
      audience: '',
      category: '',
      riskType: '',
      detailsRiskType: '',
      probability: 0,
      impactValue: 0,
      mitigationPlan: '',
      responsibleMitigationMails: [''],
      contingencyPlan: '',
      responsibleContingencyMails: [''],
    };
  }

  removeSpaces(array: string[]) {
    return array.map((i) => i.trim());
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

  validateEmptyFields() {
    if (
      this.risk.name !== '' &&
      this.risk.detectedDate != undefined &&
      this.risk.description !== '' &&
      this.risk.riskState !== '' &&
      this.risk.audience !== '' &&
      this.risk.category !== '' &&
      this.risk.riskType !== '' &&
      this.risk.detailsRiskType !== '' &&
      this.risk.probability !== 0 &&
      this.risk.impactValue !== 0 &&
      this.risk.mitigationPlan !== '' &&
      this.risk.contingencyPlan !== ''
    ) {
      return true;
    }
    return false;
  }

  closeModal() {
    this.show = false;
    this.risk = this.getEmptyRisk();
    this.errorMessage = false;
    this.criticidad = 6;
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

  criticalityLevelCalculation(probability: number, impactValue: number) {
    if (this.occurrence != undefined) {
      this.risk.probability = Number(this.occurrence);
    }
    if (this.impactValue != undefined) {
      this.risk.impactValue = Number(this.impactValue);
    }

    if (
      this.risk.probability === 0 ||
      this.risk.impactValue === 0 ||
      this.risk.impactValue == undefined ||
      this.risk.probability == undefined
    ) {
      this.criticidad = 4;

      return;
    }
    if (
      (this.risk.probability === 1 && this.risk.impactValue === 1) ||
      (this.risk.probability === 2 && this.risk.impactValue === 1) ||
      (this.risk.probability === 3 && this.risk.impactValue === 1) ||
      (this.risk.probability === 4 && this.risk.impactValue === 1) ||
      (this.risk.probability === 1 && this.risk.impactValue === 2) ||
      (this.risk.probability === 2 && this.risk.impactValue === 2) ||
      (this.risk.probability === 1 && this.risk.impactValue === 3)
    ) {
      this.criticidad = 1;

      return;
    }

    if (
      (this.risk.probability === 5 && this.risk.impactValue === 1) ||
      (this.risk.probability === 5 && this.risk.impactValue === 2) ||
      (this.risk.probability === 4 && this.risk.impactValue === 2) ||
      (this.risk.probability === 3 && this.risk.impactValue === 2) ||
      (this.risk.probability === 3 && this.risk.impactValue === 3) ||
      (this.risk.probability === 2 && this.risk.impactValue === 3) ||
      (this.risk.probability === 2 && this.risk.impactValue === 4) ||
      (this.risk.probability === 1 && this.risk.impactValue === 4) ||
      (this.risk.probability === 1 && this.risk.impactValue === 5)
    ) {
      this.criticidad = 2;
    }

    this.criticidad = 3;
  }
}
