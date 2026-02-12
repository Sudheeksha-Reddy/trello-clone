import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet],
    template: `<div style="background: #0067a3; padding: 10px; color: white; font-weight: bold;">Trello Mini</div><router-outlet></router-outlet>`
})
export class AppComponent { }