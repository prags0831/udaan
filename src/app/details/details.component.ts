import { Component, OnInit } from '@angular/core';
import { Book } from '../_services/book';
import { Flight } from '../_services/flight';
import { FlightService } from '../_services/flight.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  book!: Book;
  flight!: Flight;

  constructor(private service: FlightService, private activatedroute: ActivatedRoute, private goroute: Router) { }

  ngOnInit(): void {
    this.book = new Book();
    this.flight = new Flight();
    this.book.flight_id = this.activatedroute.snapshot.params['flight_id'];
    this.book.email = this.activatedroute.snapshot.params['email'];
    this.getOneBook();
    this.getOneFlight();
    console.log(this.book)
    console.log(this.flight)
  }

  getOneBook() {
    this.service.getOneBook(this.book.email)
      .subscribe(data => {
        this.book = data;
      });
  }
  getOneFlight() {
    this.service.getOneFlight(this.book.flight_id)
      .subscribe(data => {
        this.flight = data;
      })
  }

  gotopayment(fare: number) {
    this.goroute.navigate(['payment', fare])
  }
}

