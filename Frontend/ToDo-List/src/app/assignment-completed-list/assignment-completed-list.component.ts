import { Component, inject, OnInit } from '@angular/core';
import { AssigmentItemComponent } from '../assigment-item/assigment-item.component';
import { AssignmentService } from '../shared/services/assignment.service';
import { Assignment } from '../shared/models/Assignment.model';

@Component({
  selector: 'app-assignment-completed-list',
  imports: [AssigmentItemComponent],
  templateUrl: './assignment-completed-list.component.html',
  styleUrl: './assignment-completed-list.component.css'
})
export class AssignmentCompletedListComponent implements OnInit{
  assignmentService: AssignmentService = inject(AssignmentService);
  assignments: Assignment[] = [];

  ngOnInit(): void {
    this.assignmentService.getCompletedAssignments().subscribe({
      next: (assignments) => {
        this.assignments = assignments;
      },
      error: (err) => {
        console.log('Error fetching completed assignments: ', err);
        this.assignments = [];
      },
    });
  }

}
