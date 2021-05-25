import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Authentication } from "../_models/authentication.model";
import { JwtToken } from "../_models/jwt-token.model";
import { environment } from "../../environments/environment";
import { User } from "../_models/user.model";

@Injectable({
  providedIn: "root"
})
export class AuthService {

  token: string | null;

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token')
  }

  login(body: Authentication): void {
    this.http.post<JwtToken>(`${environment.apiUrl}/authentication/login`, body).subscribe(
      res => {
        this.token = res.token;
        localStorage.setItem('token', res.token)
      }
    )
  }

  register(body: User): Observable<any> {
    return this.http.post(`${environment.apiUrl}/authentication/register`, body)
  }
}
