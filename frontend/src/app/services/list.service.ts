import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { List } from '../models/list.model';

@Injectable({ providedIn: 'root' })
export class ListService {
    constructor(private http: HttpClient) { }

    getLists(boardId: number) {
        return this.http.get<List[]>(`${environment.apiUrl}/boards/${boardId}/lists`);
    }

    createList(boardId: number, title: string) {
        return this.http.post<List>(`${environment.apiUrl}/boards/${boardId}/lists`, { title });
    }

    updateList(boardId: number, id: number, title: string) {
        return this.http.put<List>(`${environment.apiUrl}/boards/${boardId}/lists/${id}`, { title });
    }

    deleteList(boardId: number, id: number) {
        return this.http.delete<void>(`${environment.apiUrl}/boards/${boardId}/lists/${id}`);
    }
}
