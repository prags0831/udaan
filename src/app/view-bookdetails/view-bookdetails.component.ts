import { Component, OnInit } from '@angular/core';
import { Flight } from '../_services/flight';
import { FlightService } from '../_services/flight.service';
import { Router } from '@angular/router';
import { Book } from '../_services/book';

@Component({
  selector: 'app-view-bookdetails',
  templateUrl: './view-bookdetails.component.html',
  styleUrls: ['./view-bookdetails.component.css']
})
export class ViewBookdetailsComponent implements OnInit {
  flight!: Flight[];
  message!: string;
  book!: Book[];

  constructor(private service: FlightService, private router: Router) { }

  ngOnInit(): void {
    this.getAllFlight();
    this.getAllBook();
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

  getAllBook() {
    return this.service.getAllBook()
    .subscribe(
      data => {
        this.book = data;
      }, error => {
        console.log(error);
      }
    );
  }
}
