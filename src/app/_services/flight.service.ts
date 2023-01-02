import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Flight } from './flight';
import { Book } from './book';
import { User } from './user';
import { Payment } from './payment';


@Injectable({
  providedIn: 'root'
})

export class FlightService {

  private basePathFlight = 'http://localhost:8090/rest/flight';
  private basePathBookFlight = 'http://localhost:8090/rest/flight/book';
  private basePathBook = 'http://localhost:8090/rest/bookticket';
  private basePathRegister = 'http://localhost:8090/registerNewUser';
  private basePathPayment = 'http://localhost:8090/rest/payment';
  requestHeader = new HttpHeaders({ "No-Auth": "false" });
  constructor(private http: HttpClient) { }

 
  createUser(user: User): Observable<any> {
    return this.http.post(`${this.basePathRegister}`, user, { responseType: 'text' });
  }
  

  getAllFlight(): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.basePathFlight}/all`);
  }

  deleteOneFlight(flight_id: number): Observable<any> {
    return this.http.delete(`${this.basePathFlight}/remove/${flight_id}`, { responseType: 'text' });
  }

  createFlight(flight: Flight): Observable<any> {
    return this.http.post(`${this.basePathFlight}/save`, flight, { responseType: 'text' });
  }

  getOneFlight(flight_id: number): Observable<Flight> {
    return this.http.get<Flight>(`${this.basePathFlight}/one/${flight_id}`);
  }

  updateFlight(flight_id: number, flight: Flight): Observable<any> {
    return this.http.put(`${this.basePathFlight}/modify/${flight_id}`, flight, { responseType: 'text' });
  }

  bookFlight(flight: Flight): Observable<any> {
    return this.http.post(`${this.basePathBookFlight}/save`, flight, { responseType: 'text' });
  }

  createBook(book: Book): Observable<any> {
    return this.http.post(`${this.basePathBook}/save`, book, { responseType: 'text' });
  }

  deleteOneBook(email?: String): Observable<Object> {
    return this.http.delete(`${this.basePathBook}/remove/${email}`, { responseType: 'text' });
  }

  getAllBook(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.basePathBook}/all`);
  }

  getOneBook(email: string): Observable<Book> {
    return this.http.get<Book>(`${this.basePathBook}/one/${email}`);
  }
  createPayment(payment: Payment): Observable<any> {
    return this.http.post(`${this.basePathPayment}/save`, payment, { responseType: 'text' });
  }
}