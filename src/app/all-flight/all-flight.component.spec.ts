import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllFlightComponent } from './all-flight.component';

describe('AllFlightComponent', () => {
  let component: AllFlightComponent;
  let fixture: ComponentFixture<AllFlightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllFlightComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllFlightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
