import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment-email',
  templateUrl: './payment-email.component.html',
  styleUrls: ['./payment-email.component.css']
})
export class PaymentEmailComponent implements OnInit {

  title = 'EmailTemplate';
  emailNotification: PaymentEmail = { email: '' };
  constructor(private https: HttpClient) { }

  ngOnInit(): void {

  }
  onSubmit() {
    this.https.post<PaymentEmail>('http://localhost:8090/sendEmail/getdetails', this.emailNotification).subscribe(
      res => {
        this.emailNotification = res;
        console.log(this.emailNotification);
        alert('Email Sent successfully');
        this.emailNotification.email = '';
      });
  }
}
interface PaymentEmail {
  email: string;
}
