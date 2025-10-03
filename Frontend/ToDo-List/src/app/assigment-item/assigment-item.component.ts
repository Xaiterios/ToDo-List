import { Component, inject, Input } from '@angular/core';
import { Assignment } from '../shared/models/Assignment.model';
import { AssignmentStatus } from '../shared/models/AssignmentStatus.model';
import { MatIconModule } from '@angular/material/icon';
import { AssignmentService } from '../shared/services/assignment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assigment-item',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './assigment-item.component.html',
  styleUrl: './assigment-item.component.css'
})
export class AssigmentItemComponent {
  @Input() assignment!: Assignment;
  AssignmentStatus = AssignmentStatus;

  assignmentService: AssignmentService = inject(AssignmentService);
  router: Router = inject(Router);

  updateAssignment(id: string): void {
    this.assignmentService.updateAssignment(id).subscribe({
      next: (updated) => {
        this.assignment = updated;
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate(['/home']);
        })
      },
      error: (err) => {
        console.log("Error updating assignment:", err);
      }
    });
  }
}
