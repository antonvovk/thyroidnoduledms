import { Injectable } from "@angular/core";
import { CanLoad, Route, Router, UrlSegment, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "../auth/auth.service";
import { ToastrService } from "ngx-toastr";

@Injectable()
export class AuthGuard implements CanLoad {

  constructor(private authService: AuthService,
              private router: Router,
              private toastr: ToastrService) {
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.authService.user == null) {
      this.toastr.error("Помилка авторизації");
      return this.router.createUrlTree(['auth', 'login'])
    }

    return true;
  }
}
