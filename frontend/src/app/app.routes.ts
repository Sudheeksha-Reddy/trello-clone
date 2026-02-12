import { Routes } from '@angular/router';
import { BoardsPageComponent } from './components/boards-page/boards-page.component';
import { BoardDetailPageComponent } from './components/board-detail-page/board-detail-page.component';

export const routes: Routes = [
    { path: '', component: BoardsPageComponent },
    { path: 'board/:id', component: BoardDetailPageComponent }
];