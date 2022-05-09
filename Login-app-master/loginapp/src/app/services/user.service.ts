import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = "http://localhost:9595";
  constructor(private http: HttpClient) { }
  getWelcome() {
    return this.http.get(`${this.baseUrl}/welcome`,);
  }
}
