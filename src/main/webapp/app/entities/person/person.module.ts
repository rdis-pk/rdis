import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Cov001SharedModule } from 'app/shared/shared.module';
import { PersonComponent } from './person.component';
import { PersonDetailComponent } from './person-detail.component';
import { PersonUpdateComponent } from './person-update.component';
import { PersonDeleteDialogComponent } from './person-delete-dialog.component';
import { personRoute } from './person.route';
import { PersonSearchComponent } from './person-search.component';

@NgModule({
  imports: [Cov001SharedModule, RouterModule.forChild(personRoute)],
  declarations: [PersonComponent, PersonDetailComponent, PersonUpdateComponent, PersonSearchComponent, PersonDeleteDialogComponent],
  entryComponents: [PersonDeleteDialogComponent]
})
export class Cov001PersonModule {}
