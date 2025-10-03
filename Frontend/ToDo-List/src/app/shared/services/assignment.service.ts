import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Assignment } from '../models/Assignment.model';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {
  private api: string = environment.apiUrl + 'api/assignment';
  private http: HttpClient = inject(HttpClient);

  createAssignment(title: string): Observable<Assignment> {
    return this.http.post<Assignment>(this.api, title, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    });
  }

  getAssignments(): Observable<Assignment[]> {
    return this.http.get<Assignment[]>(this.api);
  }

  getToDoAssignments(): Observable<Assignment[]> {
    return this.http.get<Assignment[]>(`${this.api}/todo`);
  }

  getInProgressAssignments(): Observable<Assignment[]> {
    return this.http.get<Assignment[]>(`${this.api}/inprogress`);
  }

  getCompletedAssignments(): Observable<Assignment[]> {
    return this.http.get<Assignment[]>(`${this.api}/completed`);
  }
}
