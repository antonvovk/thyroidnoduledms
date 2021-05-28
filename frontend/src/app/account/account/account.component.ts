import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../../auth/auth.service";
import { User } from "../../_models/user.model";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent {

  hide = true;

  form = new FormGroup({
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    middleName: new FormControl(null, [Validators.required]),
    workPlace: new FormControl(null, [Validators.required]),
    email: new FormControl({value: null, disabled: true}, [Validators.required, Validators.email]),
    oldPassword: new FormControl(null, [Validators.required, Validators.minLength(8)]),
    newPassword: new FormControl(null, [Validators.required, Validators.minLength(8)])
  })

  constructor(private authService: AuthService) {
    const user = this.authService.user
    this.form.patchValue(user)
  }

  onHideClick() {
    this.hide = !this.hide
  }

  onSubmit() {
    if (this.form.invalid) {
      this.form.markAsTouched()
      return
    }

    this.authService.updateUser(this.form.getRawValue() as User)
  }
}
