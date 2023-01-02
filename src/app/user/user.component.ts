import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from '../_services/flight';
import { FlightService } from '../_services/flight.service';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  flight!: Flight[];
  message!: string;
  plane!: Flight;

  constructor(private service: FlightService, private router: Router) { }

  ngOnInit(): void {
    this.getAllFlight();
  }

  getAllFlight() {
    return this.service.getAllFlight()
    .subscribe(
      data => {
        this.flight = data;
      }, error => {
        console.log(error);
      }
    );
  }

  bookThisFlight(flight_id: number) {
    this.router.navigate(['book', flight_id]);
  }

  bookFlight() {
    this.service.bookFlight(this.plane)
    .subscribe(data => {
      this.message = data; // read message
      this.plane = new Flight(); // clear form
    }, error => {
      console.log(error);
    });
  }
}