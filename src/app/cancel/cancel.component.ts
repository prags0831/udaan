import { Component, OnInit } from '@angular/core';
import { Book } from '../_services/book';
import { FlightService } from '../_services/flight.service';

@Component({
  selector: 'app-cancel',
  templateUrl: './cancel.component.html',
  styleUrls: ['./cancel.component.css']
})
export class CancelComponent implements OnInit {
  book!: Book;

  constructor(private service: FlightService) { }

  ngOnInit(): void {
    this.book = new Book();
  }

  getOneBook() {
    this.service.getOneBook(this.book.email).subscribe(data => {
      this.book = data;
    }, error => {
      console.log(error);
    });
  }

  deleteBook() {
    this.getOneBook();
    if (confirm('Do you want to delete?')) {
      this.service.deleteOneBook(this.book.email).subscribe(data => {
        console.log(data);
      }, error => {
        console.log(error);
      });
    } 
  }
}