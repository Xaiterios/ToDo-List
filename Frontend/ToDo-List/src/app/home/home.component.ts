import { Component } from '@angular/core';
import { TabBarComponent } from "../tab-bar/tab-bar.component";
import { AssignmentTodoListComponent } from "../assignment-todo-list/assignment-todo-list.component";
import { AssignmentInprogressListComponent } from "../assignment-inprogress-list/assignment-inprogress-list.component";
import { AssignmentCompletedListComponent } from "../assignment-completed-list/assignment-completed-list.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TabBarComponent, AssignmentTodoListComponent, AssignmentInprogressListComponent, AssignmentCompletedListComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent{

}
