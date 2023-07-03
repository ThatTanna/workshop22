import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RsvpComponent } from './components/rsvp/rsvp.component';
import { RsvpListComponent } from './components/rsvp-list/rsvp-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'rsvps', pathMatch: 'full' },
  { path: 'rsvp-list', component: RsvpListComponent },
  { path: 'rsvp-list/:name', component: RsvpListComponent },
  { path: 'rsvp', component: RsvpComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
