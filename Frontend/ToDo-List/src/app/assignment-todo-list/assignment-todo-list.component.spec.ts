import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignmentTodoListComponent } from './assignment-todo-list.component';

describe('AssignmentTodoListComponent', () => {
  let component: AssignmentTodoListComponent;
  let fixture: ComponentFixture<AssignmentTodoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignmentTodoListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignmentTodoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
