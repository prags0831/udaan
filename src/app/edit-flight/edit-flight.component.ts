import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from '../_services/flight';
import { FlightService } from '../_services/flight.service';

@Component({
  selector: 'app-edit-flight',
  templateUrl: './edit-flight.component.html',
  styleUrls: ['./edit-flight.component.css']
})
export class EditFlightComponent implements OnInit {
  flight_id!: number;
  flight!: Flight;

  // inject service and acivated Route param
  constructor(private service: FlightService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.flight_id = this.activatedRoute.snapshot.params['flight_id'];
    
    this.service.getOneFlight(this.flight_id).subscribe(
      data => {
      this.flight = data;
      console.log(this.flight);
    }, error => {
      console.log(error);
    });
  }

  updateFlight() {
    if (confirm('Updated Flight Details Successfull!!!')) {
    this.service.updateFlight(this.flight_id, this.flight)
    .subscribe( data => {
      console.log(data);
      this.router.navigate(['allflight']);
    });
  }
}
}