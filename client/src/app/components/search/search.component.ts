import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Rsvp } from 'src/app/models/Rsvp.model';
import { RsvpService } from 'src/app/services/rsvp.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  rsvps?: Rsvp[];
  currentRsvp: Rsvp = {};
  currentIndex = -1;
  name = '';

  searchForm!: FormGroup


  constructor(private fb: FormBuilder, private rsvpService: RsvpService, private router: Router) { }

  ngOnInit(): void {
    this.searchForm = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
    })
  }

  searchName(): void {
    this.currentRsvp = {};
    this.currentIndex = -1;

    this.rsvpService.getName(this.name)
      .subscribe({
        next: (data) => {
          this.rsvps = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });

  }

  // search() {
  //   this.name = this.searchForm.value['name'];
  //   console.log(this.name);
  //   if (this.searchForm.valid) {
  //     let url = `api/rsvp?q=` + this.name;
  //     this.rsvpService.getName(this.name).subscribe({
  //       next: (data: any) => {
  //         console.log(url);
  //         this.searchForm = data;
  //         this.router.navigate(['/search-results', this.name])
  //       },
  //       error: (error: any) => {
  //         console.error("An error has occurred: ", error);
  //       }
  //     })
  //   }
  // }

  // search() {
  //   console.log(this.name);
  //   if (this.searchForm.valid) {
  //     let url = "/api/rsvp/search";
  //     this.http.post(url, this.searchForm.value).subscribe({
  //       next: (data: any) => {
  //         console.log("Searching for.. " + data);
  //       }, error: (error: any) => {
  //         console.error("Not found: ", error);
  //       }
  //     }
  //     )
  //   }
  // }


}

