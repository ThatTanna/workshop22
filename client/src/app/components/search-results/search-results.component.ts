import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Rsvp } from 'src/app/models/Rsvp.model';
import { RsvpService } from 'src/app/services/rsvp.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent {
  name!: string;
  rsvpList = new Array<Rsvp>();

  constructor(public rsvpService: RsvpService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.name = this.activatedRoute.snapshot.params['name']
    this.rsvpService.getName(this.name).subscribe({
      next: (data: any) => {
        this.rsvpList = data;
      },
      error: (error: any) => {
        console.error("An error has occurred: ", error);
      }
    })
    }

    displayedColumns: string[] = ['name', 'email', 'phone', 'date', 'comment'];
}
