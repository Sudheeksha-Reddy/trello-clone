import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Board } from '../models/board.model';

@Injectable({ providedIn: 'root' })
export class BoardService {
    constructor(private http: HttpClient) { }
    getBoards() { return this.http.get<Board[]>(`${environment.apiUrl}/boards`); }
    createBoard(name: string) { return this.http.post<Board>(`${environment.apiUrl}/boards`, { name }); }
    deleteBoard(id: number) { return this.http.delete(`${environment.apiUrl}/boards/${id}`); }
}