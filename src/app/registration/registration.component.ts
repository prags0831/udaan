import { Component, OnInit } from '@angular/core';

import { User } from '../_services/user';
import { FlightService } from '../_services/flight.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user!: User;

  constructor(private service: FlightService) { }

  ngOnInit(): void {
    this.user = new User();
  }

  createUser() {
    // if(confirm("Successfully registered!!!")) {
    this.service.createUser(this.user)
    .subscribe(data => {
      this.user = new User(); 
      alert("success")
    }, error => {
      console.log(error);
    });
  }
}