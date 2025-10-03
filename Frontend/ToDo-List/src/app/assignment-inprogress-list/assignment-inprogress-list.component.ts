import { Component, inject, OnInit } from '@angular/core';
import { AssignmentService } from '../shared/services/assignment.service';
import { Assignment } from '../shared/models/Assignment.model';
import { AssigmentItemComponent } from '../assigment-item/assigment-item.component';

@Component({
  selector: 'app-assignment-inprogress-list',
  imports: [AssigmentItemComponent],
  templateUrl: './assignment-inprogress-list.component.html',
  styleUrl: './assignment-inprogress-list.component.css'
})
export class AssignmentInprogressListComponent implements OnInit{
  assignmentService: AssignmentService = inject(AssignmentService);
  assignments: Assignment[] = [];

  ngOnInit(): void {
    this.assignmentService.getInProgressAssignments().subscribe({
      next: (assignments) => {
        this.assignments = assignments;
      },
      error: (err) => {
        console.log('Error fetching inprogress assignments: ', err);
        this.assignments = [];
      },
    });
  }
}
