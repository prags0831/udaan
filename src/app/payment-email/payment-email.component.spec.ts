import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentEmailComponent } from './payment-email.component';

describe('PaymentEmailComponent', () => {
  let component: PaymentEmailComponent;
  let fixture: ComponentFixture<PaymentEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentEmailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
