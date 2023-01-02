import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from '../_services/flight';
import { FlightService } from '../_services/flight.service';

@Component({
  selector: 'app-all-flight',
  templateUrl: './all-flight.component.html',
  styleUrls: ['./all-flight.component.css']
})
export class AllFlightComponent implements OnInit {
  flight!: Flight[];
  message!: string;

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

  deleteFlight(flight_id: number) {
    if (confirm('Do you want to delete?')) {
      this.service.deleteOneFlight(flight_id).subscribe(data => {
        this.message = data;
        this.getAllFlight();
      }, error => {
        console.log(error);
      });
    } else {
      this.message = '';
    }
  }

  // tslint:disable-next-line: typedef
  editFlight(id: number) {
      this.router.navigate(['flightedit', id]);
    }
}