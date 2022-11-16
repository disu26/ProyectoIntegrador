import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Risk } from '../models/risk';
import { faHeartCirclePlus, faArrowUpRightFromSquare, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Table } from 'primeng/table';
import { SelectItem } from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';
import { RiskService } from '../service/risk.service';


@Component({
  selector: 'app-risk-table',
  templateUrl: './risk-table.component.html',
  styleUrls: ['./risk-table.component.scss'],
})
export class RiskTableComponent implements OnInit {

  selectedRisks: Risk[] = [];
  
  criticValue!: any[];

  riskState!: any[];

  audience!: any[];

  category!: any[];

  riskType!: any[];

  loading: boolean = true;

  occurrence?: string;

  impactValue?: string;

  criticidad?: number;

  faHeartCirclePlus = faHeartCirclePlus;
  faArrowUpRightFromSquare = faArrowUpRightFromSquare;
  faTrashCan = faTrashCan;

  @ViewChild('dt') table?: Table;
  
  @Input() projectId?: string;
  @Input() risks: Risk[] = [];
  constructor(private primengConfig: PrimeNGConfig, private service: RiskService) {
  }

  ngOnInit() {
    this.criticValue = [
      {label: 'Bajo', value: 'Bajo'},
      {label: 'Medio', value: 'Medio'},
      {label: 'Alto', value: 'Alto'},
    ]

    this.riskState = [
      {label:'Abierto', value:'Abierto'},
      {label:'Mitigado', value:'Mitigado'},
      {label:'Cerrado', value:'Cerrado'},
      {label:'Problema', value:'Problema'},
    ]

    this.audience = [
      {label:'Interna', value:'Interna'},
      {label:'Externa', value:'Externa'},
    ]

    this.category = [
      {label:'Costo', value:'Costo'},
      {label:'Tiempo', value:'Tiempo'},
      {label:'Calidad', value:'Calidad'},
    ]

    this.riskType = [
      {label:'Riesgo de producto o calidad', value:'Riesgo de producto o calidad'},
      {label:'Riesgo de proyecto', value:'Riesgo de proyecto'},
    ]

    this.primengConfig.ripple = true;
  }

  onDetectedDateSelect(value: any){
    if(this.table !== undefined){
      this.table.filter(this.formatDate(value), 'detectedDate', 'equals');
    }
  }

  onEndingDateSelect(value: any){
    if(this.table !== undefined){
      this.table.filter(this.formatDate(value), 'endedDate', 'equals');
    }
  }

  formatDate(date: any) {
    let month = date.getMonth() + 1;
    let day = date.getDate();

    return date.getFullYear() + ',' + month + ',' + day;
}
   
  deleteRisk(risk: Risk) {
    risk.state = 0;
    this.service.updateRisk(risk).subscribe(() => {
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    });
  }

  criticalityLevelCalculation(risk: Risk) {
    if (this.occurrence != undefined) {
      risk.probability = Number(this.occurrence);
    }
    if (this.impactValue != undefined) {
      risk.impactValue = Number(this.impactValue);
    }

    if (
      risk.probability === 0 ||
      risk.impactValue === 0 ||
      risk.impactValue == undefined ||
      risk.probability == undefined
    ) {
      this.criticidad = 4;

      return;
    }
    if (
      (risk.probability === 1 && risk.impactValue === 1) ||
      (risk.probability === 2 && risk.impactValue === 1) ||
      (risk.probability === 3 && risk.impactValue === 1) ||
      (risk.probability === 4 && risk.impactValue === 1) ||
      (risk.probability === 1 && risk.impactValue === 2) ||
      (risk.probability === 2 && risk.impactValue === 2) ||
      (risk.probability === 1 && risk.impactValue === 3)
    ) {
      this.criticidad = 1;

      return;
    }

    if (
      (risk.probability === 5 && risk.impactValue === 1) ||
      (risk.probability === 5 && risk.impactValue === 2) ||
      (risk.probability === 4 && risk.impactValue === 2) ||
      (risk.probability === 3 && risk.impactValue === 2) ||
      (risk.probability === 3 && risk.impactValue === 3) ||
      (risk.probability === 2 && risk.impactValue === 3) ||
      (risk.probability === 2 && risk.impactValue === 4) ||
      (risk.probability === 1 && risk.impactValue === 4) ||
      (risk.probability === 1 && risk.impactValue === 5)
    ) {
      this.criticidad = 2;
    }

    this.criticidad = 3;
  }
}
