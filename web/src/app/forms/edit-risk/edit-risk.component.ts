import { Risk } from './../../models/risk';
import { RiskService } from './../../service/risk.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-risk',
  templateUrl: './edit-risk.component.html',
  styleUrls: ['./edit-risk.component.css'],
})
export class EditRiskComponent implements OnInit {
  @Input() risk: Risk | undefined;
  occurrence?: string;
  impactValue?: string;
  tags?: string;
  contingenceMails?: string;
  mitigationMails?: string;

  constructor(private service: RiskService) {}

  ngOnInit(): void {}

  ngOnChanges() {
    this.getvalues();
  }

  editRisk() {
    if (this.tags != undefined && this.risk != undefined) {
      this.risk.labels = this.tags.split(',');
    }
    if (this.contingenceMails != undefined && this.risk != undefined) {
      this.risk.responsibleContingencyMails = this.contingenceMails.split(',');
    }

    if (this.mitigationMails != undefined && this.risk != undefined) {
      this.risk.responsibleMitigationMails = this.mitigationMails.split(',');
    }
    if (this.risk != undefined) {
      this.service.updateRisk(this.risk).subscribe(() => {
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      });
    }
  }

  getvalues() {
    this.tags = this.risk?.labels.join(',');
    this.contingenceMails = this.risk?.responsibleContingencyMails.join(',');
    this.mitigationMails = this.risk?.responsibleMitigationMails.join(',');
  }
}
