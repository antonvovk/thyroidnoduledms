import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from "../auth/auth.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,
              private toastr: ToastrService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status === 401) {
        this.authService.logout()
        this.toastr.error("Помилка авторизації");
      } else {
        this.toastr.error(err.error.message);
      }

      return throwError(err);
    }));
  }
}
