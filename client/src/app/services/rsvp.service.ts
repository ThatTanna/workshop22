import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Observer, catchError, lastValueFrom, retry, tap, throwError } from 'rxjs';
import { Rsvp } from '../models/Rsvp.model';

const url = "/api";

@Injectable({
  providedIn: 'root'
})

export class RsvpService {


  rsvpList!: Rsvp[];

  constructor(private http: HttpClient) { }

  getAllRsvp(): Observable<Rsvp[]> {
    return this.http.get<Rsvp[]>(`${url}/rsvps`);
  }

  getName(name: any): Observable<Rsvp[]> {
    return this.http.get<Rsvp[]>(`${url}/rsvp?q=${name}`);
  }

  updateByEmail(email: string, rsvp: Rsvp): Observable<Rsvp[]> {
    return this.http.put<Rsvp[]>(`${url}/rsvp/${email}`, rsvp);
  }

  create(rsvp: any): Observable<Rsvp[]> {
    return this.http.post<Rsvp[]>("/api/rsvps/register", rsvp);
  }

  // handleError(error: any) {
  //   let errorMessage = "";
  //   if (error.error instanceof ErrorEvent) {
  //     errorMessage = error.errorMessage;
  //   } else {
  //     errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
  //   }
  //   window.alert(errorMessage);
  //   return throwError(() => {
  //     return errorMessage;
  //   });
  // }

}
