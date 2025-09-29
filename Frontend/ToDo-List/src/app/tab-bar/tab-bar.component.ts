import { Component, inject } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AssignmentService } from '../shared/services/assignment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tab-bar',
  imports: [MatIconModule, ReactiveFormsModule],
  templateUrl: './tab-bar.component.html',
  styleUrl: './tab-bar.component.css'
})
export class TabBarComponent {
  fb: FormBuilder = inject(FormBuilder);
  assignmentService: AssignmentService = inject(AssignmentService);
  router: Router = inject(Router);

  createForm: FormGroup = this.fb.group({
    title: ['', Validators.required]
  });

  onSubmit(){
    if (this.createForm.valid) {
      const newAssignmentTitle: string = { ...this.createForm.value };
      this.assignmentService.createAssignment(newAssignmentTitle).subscribe(() => {
        this.createForm.reset();
        this.router.navigate(['/home']);
      });
    }
  }
}
