import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  hide = true;

  form = new FormGroup({
    username: new FormControl(null, [Validators.required, Validators.email]),
    password: new FormControl(null, [Validators.required, Validators.minLength(8)])
  })

  constructor(private authService: AuthService) {
  }

  onHideClick() {
    this.hide = !this.hide
  }

  onSubmit() {
    if (this.form.invalid) {
      this.form.markAsTouched()
      return
    }

    this.authService.login(this.form.value)
  }
}
