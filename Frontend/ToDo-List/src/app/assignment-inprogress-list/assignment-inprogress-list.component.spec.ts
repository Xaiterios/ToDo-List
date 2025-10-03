import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignmentInprogressListComponent } from './assignment-inprogress-list.component';

describe('AssignmentInprogressListComponent', () => {
  let component: AssignmentInprogressListComponent;
  let fixture: ComponentFixture<AssignmentInprogressListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignmentInprogressListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignmentInprogressListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
