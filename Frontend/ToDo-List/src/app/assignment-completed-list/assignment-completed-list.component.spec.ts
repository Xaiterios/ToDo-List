import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignmentCompletedListComponent } from './assignment-completed-list.component';

describe('AssignmentCompletedListComponent', () => {
  let component: AssignmentCompletedListComponent;
  let fixture: ComponentFixture<AssignmentCompletedListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignmentCompletedListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignmentCompletedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
