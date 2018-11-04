import { Component, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  constructor(private renderer: Renderer2) {
    this.renderer.addClass(document.body, 'font-setup');
   }
}
