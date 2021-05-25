import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  hide = true;

  form = new FormGroup({
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    middleName: new FormControl(null, [Validators.required]),
    workPlace: new FormControl(null, [Validators.required]),
    email: new FormControl(null, [Validators.required, Validators.email]),
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

    this.authService.register(this.form.value).subscribe(() => {})
  }
}
