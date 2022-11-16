import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../service/project.service';
import { Risk } from '../models/risk';

@Component({
  selector: 'app-matriz-riesgo',
  templateUrl: './matriz-riesgo.component.html',
  styleUrls: ['./matriz-riesgo.component.css'],
})
export class MatrizRiesgoComponent implements OnInit {
  risk!: Risk;

  cols!: any[];

  id!: string | null;

  _selectedEstados!: any[];

  matrizBi: Array<Array<number>> = [
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
  ];

  constructor(private service: ProjectService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.id = id;

    this.metodoInicial();

    this.cols = [
      { field: 'Abierto', header: 'Abierto' },
      { field: 'Mitigado', header: 'Mitigado' },
      { field: 'Cerrado', header: 'Cerrado' },
      { field: 'Problema', header: 'Problema' },
    ];

    this._selectedEstados = this.cols;
  }

  @Input() get selectedEstados(): any[] {
    return this._selectedEstados;
  }

  set selectedEstados(val: any[]) {
    // restaura orden original
    this._selectedEstados = this.cols.filter((col) => val.includes(col));

    // limpiar la matriz
    this.matrizBi = [
      [0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0],
    ];

    //si se selecciona todos los filtros
    if (this._selectedEstados.length === 4) {
      this.metodoInicial();
      return;
    }

    //filtros para la matriz
    this._selectedEstados.filter((col) => {
      if (col.field === 'Abierto') {
        this.metodoAbierto();
      } else if (col.field === 'Mitigado') {
        this.metodoMitigado();
      } else if (col.field === 'Cerrado') {
        this.metodoCerrado();
      } else if (col.field === 'Problema') {
        this.metodoProblema();
      }
    });
  }

  metodoInicial() {
    this.service.getProject(this.id!).subscribe((data) => {
      for (let riesgo = 0; riesgo < data.risks.length; riesgo++) {
        this.risk = data.risks[riesgo];
        console.log(data.risks[riesgo]);
        this.pintarMatriz();
      }
    });
  }

  metodoAbierto() {
    this.service.getProject(this.id!).subscribe((data) => {
      for (let riesgo = 0; riesgo < data.risks.length; riesgo++) {
        this.risk = data.risks[riesgo];
        if (this.risk.riskState === 'Abierto') {
          this.pintarMatriz();
        }
      }
    });
  }

  metodoMitigado() {
    this.service.getProject(this.id!).subscribe((data) => {
      for (let riesgo = 0; riesgo < data.risks.length; riesgo++) {
        this.risk = data.risks[riesgo];
        if (this.risk.riskState === 'Mitigado') {
          this.pintarMatriz();
        }
      }
    });
  }

  metodoCerrado() {
    this.service.getProject(this.id!).subscribe((data) => {
      for (let riesgo = 0; riesgo < data.risks.length; riesgo++) {
        this.risk = data.risks[riesgo];
        if (this.risk.riskState === 'Cerrado') {
          this.pintarMatriz();
        }
      }
    });
  }

  metodoProblema() {
    this.service.getProject(this.id!).subscribe((data) => {
      for (let riesgo = 0; riesgo < data.risks.length; riesgo++) {
        this.risk = data.risks[riesgo];
        if (this.risk.riskState === 'Problema') {
          this.pintarMatriz();
        }
      }
    });
  }

  pintarMatriz() {
    for (let fila = 0; fila < this.matrizBi.length; fila++) {
      for (let columna = 0; columna < this.matrizBi.length; columna++) {
        if (
          this.risk.probability - 1 == fila &&
          this.risk.impactValue - 1 == columna
        ) {
          this.matrizBi[fila][columna] += 1;
        }
      }
    }
  }
}
