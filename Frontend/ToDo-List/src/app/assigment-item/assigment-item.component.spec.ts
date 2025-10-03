import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssigmentItemComponent } from './assigment-item.component';

describe('AssigmentItemComponent', () => {
  let component: AssigmentItemComponent;
  let fixture: ComponentFixture<AssigmentItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssigmentItemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssigmentItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
