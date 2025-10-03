import { Component, Input } from '@angular/core';
import { Assignment } from '../shared/models/Assignment.model';
import { AssignmentStatus } from '../shared/models/AssignmentStatus.model';
import { MatIconModule } from '@angular/material/icon';

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
}
