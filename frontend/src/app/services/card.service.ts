import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Card } from '../models/card.model';

@Injectable({ providedIn: 'root' })
export class CardService {
    constructor(private http: HttpClient) { }

    getCards(listId: number) {
        return this.http.get<Card[]>(`${environment.apiUrl}/lists/${listId}/cards`);
    }

    createCard(listId: number, title: string, description: string = '') {
        return this.http.post<Card>(`${environment.apiUrl}/lists/${listId}/cards`, {
            title,
            description
        });
    }

    updateCard(listId: number, cardId: number, title: string, description: string = '') {
        return this.http.put<Card>(`${environment.apiUrl}/lists/${listId}/cards/${cardId}`, {
            title,
            description
        });
    }

    deleteCard(listId: number, cardId: number) {
        return this.http.delete<void>(`${environment.apiUrl}/lists/${listId}/cards/${cardId}`);
    }
}
