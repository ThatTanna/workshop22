import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RsvpService } from 'src/app/services/rsvp.service';

@Component({
  selector: 'app-rsvp',
  templateUrl: './rsvp.component.html',
  styleUrls: ['./rsvp.component.css']
})
export class RsvpComponent {
  headerTitle = "RSVP Form";

  rsvpForm!: FormGroup

  constructor(private fb: FormBuilder, private rsvpService: RsvpService) { }

  ngOnInit(): void {
    this.rsvpForm = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required]),
      phone: this.fb.control<string>('', [Validators.required]),
      date: this.fb.control<Date>(new Date, [Validators.required]),
      comment: this.fb.control<string>(''),
    })
  }

  register(): void {
    console.log(this.rsvpForm.value);
    if (this.rsvpForm.valid) {
      this.rsvpService.create(this.rsvpForm.value)
        .subscribe({
          next: (data) => {
            console.log("Registration success!", data);
          },
          error: (error) => {
            console.error("Error registering: ", error);
          }
        });
    }
  }

  update(): void {
    console.log(this.rsvpForm.value);
    if (this.rsvpForm.valid) {
      const email = this.rsvpForm.value.email;
      this.rsvpService.updateByEmail(email, this.rsvpForm.value)
        .subscribe({
          next: (data) => {
            console.log("Update success!", data);
          },
          error: (error) => {
            console.error("Error updating: ", error);
          }
        })
    }
  }

}
