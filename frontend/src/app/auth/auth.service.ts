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

  constructor(private http: HttpClient) {
  }

  login(body: Authentication): void {
    this.http.post<JwtToken>(`${environment.apiUrl}/authentication/login`, body).subscribe(
      res => {
        console.log(res);
      }
    )
  }

  register(body: User): Observable<any> {
    return this.http.post(`${environment.apiUrl}/authentication/register`, body)
  }
}
