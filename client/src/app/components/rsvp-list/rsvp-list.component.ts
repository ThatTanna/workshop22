import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, catchError, throwError } from 'rxjs';
import { Rsvp } from 'src/app/models/Rsvp.model';
import { RsvpService } from 'src/app/services/rsvp.service';


@Component({
  selector: 'app-rsvp-list',
  templateUrl: './rsvp-list.component.html',
  styleUrls: ['./rsvp-list.component.css']
})
export class RsvpListComponent implements OnInit {

  rsvpList!: Rsvp[];
  currentRsvp: Rsvp = {};
  currentIndex = -1;
  name = '';

  searchForm!: FormGroup

  constructor(private fb: FormBuilder, public rsvpService: RsvpService) { }

  ngOnInit(): void {
    this.searchForm = this.createForm();
    this.loadRsvpList();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
    })
  }

  loadRsvpList(): void {
    this.rsvpService.getAllRsvp()
      .subscribe({
        next: (data) => {
          this.rsvpList = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  displayedColumns: string[] = ['name', 'email', 'phone', 'date', 'comment'];

  searchName(): void {
    const searchName = this.searchForm.value.name;
    this.currentRsvp = {};
    this.currentIndex = -1;
    this.rsvpService.getName(searchName)
      .subscribe({
        next: (data) => {
          this.rsvpList = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });

  }

}
