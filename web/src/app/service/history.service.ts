import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { History } from '../models/history';

@Injectable({
    providedIn: 'root',
})
export class HistoryService {
    private url = 'https://matriz-riesgos.herokuapp.com/';
    constructor(private http: HttpClient) {}

    getHistory(): Observable<any> {
        let direction = this.url + 'getAllHistory/'
        return this.http.get<History>(direction);
    }
}
