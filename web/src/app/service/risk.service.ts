import { Observable } from 'rxjs';
import { Risk } from './../models/risk';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class RiskService {
  private url = 'https://matriz-riesgos.herokuapp.com/';
  constructor(private http: HttpClient) {}

  saveRisk(risk: Risk): Observable<any> {
    let direction = this.url + 'createRisk';
    return this.http.post<any>(direction, risk, {
      responseType: 'text' as 'json',
    });
  }

  updateRisk(risk: Risk): Observable<any> {
    let direction = this.url + 'updateRisk';
    return this.http.put<any>(direction, risk, {
      responseType: 'text' as 'json',
    });
  }
}

