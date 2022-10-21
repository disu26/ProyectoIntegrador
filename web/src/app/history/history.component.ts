import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { Table } from 'primeng/table';
import { History } from 'src/app/models/history';
import { HistoryService } from 'src/app/service/history.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  @ViewChild('dt') table?: Table;

  history: History[] = [];

  constructor(private historyService: HistoryService, private primengConfig: PrimeNGConfig) { }

  ngOnInit(): void {
    this.getHistory();
    this.primengConfig.ripple = true;
  }

  getHistory() {
    this.historyService.getHistory().subscribe(
      (data: History[]) => {
        this.history = data;
      }
    )
  }


}
