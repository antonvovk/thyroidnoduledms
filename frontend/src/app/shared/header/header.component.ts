import { Component, Input } from '@angular/core';
import { AuthService } from "../../auth/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  @Input() hide = false

  constructor(private authService: AuthService) {
  }

  getName(): string {
    return `${this.authService.user.firstName} ${this.authService.user.lastName}`
  }

  show(): boolean {
    return this.authService.user != null
  }

  logout(): void {
    this.authService.logout()
  }
}
