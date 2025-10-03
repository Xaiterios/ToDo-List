import { Component, inject, OnInit } from '@angular/core';
import { AssignmentService } from '../shared/services/assignment.service';
import { Assignment } from '../shared/models/Assignment.model';
import { AssigmentItemComponent } from '../assigment-item/assigment-item.component';

@Component({
  selector: 'app-assignment-todo-list',
  standalone: true,
  imports: [AssigmentItemComponent],
  templateUrl: './assignment-todo-list.component.html',
  styleUrl: './assignment-todo-list.component.css'
})
export class AssignmentTodoListComponent implements OnInit{
  assignmentService: AssignmentService = inject(AssignmentService);
  assignments: Assignment[] = [];

  ngOnInit(): void {
    this.assignmentService.getToDoAssignments().subscribe({
      next: (assignments) => {
        this.assignments = assignments;
      },
      error: (err) => {
        console.log('Error fetching todo assignments: ', err);
        this.assignments = [];
      },
    });
  }
}
