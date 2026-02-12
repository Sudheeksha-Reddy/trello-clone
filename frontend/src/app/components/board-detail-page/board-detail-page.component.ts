import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ListService } from '../../services/list.service';
import { CardService } from '../../services/card.service';
import { List } from '../../models/list.model';
import { Card } from '../../models/card.model';

@Component({
    selector: 'app-board-detail-page',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './board-detail-page.component.html',
    styleUrls: ['./board-detail-page.component.css']
})
export class BoardDetailPageComponent implements OnInit {
    boardId!: number;
    lists: List[] = [];
    cardsMap: { [key: number]: Card[] } = {};
    newListTitle = '';
    errorMsg = '';

    constructor(
        private route: ActivatedRoute,
        private listService: ListService,
        private cardService: CardService
    ) { }

    ngOnInit() {
        this.boardId = Number(this.route.snapshot.paramMap.get('id'));
        this.loadLists();
    }

    loadLists() {
        this.errorMsg = '';
        this.listService.getLists(this.boardId).subscribe({
            next: (data) => {
                this.lists = data;
                this.lists.forEach(l => this.loadCards(l.id));
            },
            error: (err) => {
                console.error('loadLists failed', err);
                this.errorMsg = 'Failed to load lists. Check backend/proxy and console logs.';
            }
        });
    }

    loadCards(listId: number) {
        this.cardService.getCards(listId).subscribe({
            next: (data) => (this.cardsMap[listId] = data),
            error: (err) => console.error(`loadCards failed for list ${listId}`, err)
        });
    }

    addList() {
        if (!this.newListTitle?.trim()) return;

        this.listService.createList(this.boardId, this.newListTitle.trim()).subscribe({
            next: () => {
                this.newListTitle = '';
                this.loadLists();
            },
            error: (err) => {
                console.error('addList failed', err);
                this.errorMsg = 'Failed to create list. Check console logs.';
            }
        });
    }

    addCard(listId: number, input: HTMLInputElement) {
        const title = input.value?.trim();
        if (!title) return;

        this.cardService.createCard(listId, title, '').subscribe({
            next: () => {
                input.value = '';
                this.loadCards(listId);
            },
            error: (err) => {
                console.error('addCard failed', err);
                this.errorMsg = 'Failed to create card. Check console logs.';
            }
        });
    }

    delList(listId: number) {
        this.listService.deleteList(this.boardId, listId).subscribe({
            next: () => this.loadLists(),
            error: (err) => {
                console.error('delList failed', err);
                this.errorMsg = 'Failed to delete list. Check console logs.';
            }
        });
    }

    delCard(listId: number, cardId: number) {
        this.cardService.deleteCard(listId, cardId).subscribe({
            next: () => this.loadCards(listId),
            error: (err) => {
                console.error('delCard failed', err);
                this.errorMsg = 'Failed to delete card. Check console logs.';
            }
        });
    }
}
