import { Component, OnInit } from '@angular/core';
import { Flight } from '../_services/flight';
import { FlightService } from '../_services/flight.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  flight!: Flight;
  message!: string;

  // inject service class
  constructor(private service: FlightService) { }

  ngOnInit(): void {
    // when page is loaded clear form data
    this.flight = new Flight();
  }

  createFlight() {
    if (confirm('Flight added successfully!!!?')) {
    this.service.createFlight(this.flight)
    .subscribe(data => {
      this.flight = new Flight();
    }
  , error => {
      console.log(error);
    });
  }
  }
}