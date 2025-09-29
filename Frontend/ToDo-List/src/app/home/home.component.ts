import { Component } from '@angular/core';
import { TabBarComponent } from "../tab-bar/tab-bar.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TabBarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
