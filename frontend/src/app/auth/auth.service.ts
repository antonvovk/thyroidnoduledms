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
    const token = localStorage.getItem('token')
    if (token) {
      this._token = token
    }

    const user = localStorage.getItem('user')
    if (user) {
      this._user = JSON.parse(user)
    }
  }

  private _token: string | null = null

  get token(): string | null {
    return this._token
  }

  private _user: User | null = null

  get user(): User | null {
    return this._user
  }

  login(body: Authentication): void {
    this.http.post<JwtToken>(`${environment.apiUrl}/authentication/login`, body).subscribe(
      res => {
        if (res.token && res.user) {
          this._token = res.token;
          this._user = res.user

          localStorage.setItem('token', res.token)
          localStorage.setItem('user', JSON.stringify(res.user))
        }
      }
    )
  }

  register(body: User): Observable<any> {
    return this.http.post(`${environment.apiUrl}/authentication/register`, body)
  }
}
