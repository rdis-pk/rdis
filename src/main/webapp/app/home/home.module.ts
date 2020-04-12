import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Cov001SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Cov001SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class Cov001HomeModule {}
