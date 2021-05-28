import { Injectable } from "@angular/core";
import { CanLoad, Route, Router, UrlSegment, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "../auth/auth.service";
import { ToastrService } from "ngx-toastr";

@Injectable()
export class QualificationTestedGuard implements CanLoad {

  constructor(private authService: AuthService,
              private router: Router,
              private toastr: ToastrService) {
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.authService.user.qualificationTestPassed) {
      this.toastr.warning("Ви ще не пройшли перевірку кваліфікації")
      return this.router.createUrlTree(['qualification', 'testing'])
    }

    return true;
  }
}
