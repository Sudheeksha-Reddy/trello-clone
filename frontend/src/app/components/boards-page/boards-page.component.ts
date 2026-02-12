import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { BoardService } from '../../services/board.service';
import { Board } from '../../models/board.model';

@Component({
    selector: 'app-boards-page',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './boards-page.component.html',
    styleUrls: ['./boards-page.component.css']
})
export class BoardsPageComponent implements OnInit {
    boards: Board[] = [];
    newName = '';
    errorMsg = '';

    constructor(private service: BoardService, private router: Router) { }

    ngOnInit() {
        this.load();
    }

    load() {
        this.errorMsg = '';
        this.service.getBoards().subscribe({
            next: (d) => (this.boards = d),
            error: (err) => {
                console.error('getBoards failed', err);
                this.errorMsg = 'Failed to load boards. Check backend/proxy and console logs.';
            }
        });
    }

    add() {
        const name = this.newName?.trim();
        if (!name) return;

        this.service.createBoard(name).subscribe({
            next: () => {
                this.newName = '';
                this.load();
            },
            error: (err) => {
                console.error('createBoard failed', err);
                this.errorMsg = 'Failed to create board. Check console logs.';
            }
        });
    }

    open(id: number) {
        this.router.navigate(['/board', id]);
    }

    del(id: number) {
        this.service.deleteBoard(id).subscribe({
            next: () => this.load(),
            error: (err) => {
                console.error('deleteBoard failed', err);
                this.errorMsg = 'Failed to delete board. Check console logs.';
            }
        });
    }
}
