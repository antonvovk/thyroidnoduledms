import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Authentication } from "../_models/authentication.model";
import { JwtToken } from "../_models/jwt-token.model";
import { environment } from "../../environments/environment";
import { User } from "../_models/user.model";
import { Router } from "@angular/router";
import * as bcrypt from 'bcryptjs';
import { ToastrService } from "ngx-toastr";

@Injectable({
  providedIn: "root"
})
export class AuthService {

  constructor(private http: HttpClient,
              private router: Router,
              private toastrService: ToastrService) {
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

  qualify(qualificationTestPassed: boolean) {
    this._user.qualificationTestPassed = qualificationTestPassed
    localStorage.setItem('user', JSON.stringify(this._user))
  }

  login(body: Authentication, url: string[] = ['analyses']): void {
    this.http.post<JwtToken>(`${environment.apiUrl}/authentication/login`, body).subscribe(
      res => {
        if (res.token && res.user) {
          this._token = res.token;
          this._user = res.user

          localStorage.setItem('token', res.token)
          localStorage.setItem('user', JSON.stringify(res.user))

          this.router.navigate(url)
        }
      }
    )
  }

  register(body: User): void {
    bcrypt.hash(body.password, 10).then(hashedPass => {
      this.http.post(`${environment.apiUrl}/authentication/register`, {
        user: body,
        passwordHash: hashedPass
      }).subscribe(() => {
        this.login(<Authentication>{
            username: body.email,
            password: body.password
          },
          ['qualification', 'testing'])
      })
    });
  }

  updateUser(user: User): void {
    this.http.put<User>(`${environment.apiUrl}/authentication`, user).subscribe(res => {
      this.toastrService.info("Успішно оновлено дані")

      if (res) {
        this._user = res
        localStorage.setItem('user', JSON.stringify(res))
      }
    }, () => {
      this.toastrService.error("Помилка оновлення данних")
    });
  }

  logout(): void {
    localStorage.removeItem('token')
    localStorage.removeItem('user')

    this._token = null
    this._user = null

    this.router.navigate(['auth', 'login'])
  }
}
