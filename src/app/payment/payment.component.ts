import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FlightService } from '../_services/flight.service';
import ShortUniqueId from 'short-unique-id';
import { Payment } from '../_services/payment';
import { AdminComponent } from '../admin/admin.component';

import { Flight } from '../_services/flight';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  payment!: Payment;

  constructor(private https: HttpClient, private service: FlightService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.payment = new Payment();
    this.payment.amount = this.activatedRoute.snapshot.params['fare'];
    console.log(this.payment.amount)
  }
  successpage() {
    this.router.navigateByUrl('/paymentsuccess')
  }

  createPayment() {
    if (confirm('Your Payment is Successfull!!!')) {
    console.log(this.payment)
    this.service.createPayment(this.payment)
      .subscribe(data => {
        // this.message = data; // read message
        this.payment = new Payment(); // clear form
      }, error => {
        console.log(error);
      });
  }
}

  public pnrgenerate() {
    const uuid = new ShortUniqueId({ length: 5 })
    this.payment.pnr = uuid()
  }
}
