import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Cov001SharedModule } from 'app/shared/shared.module';
import { Cov001CoreModule } from 'app/core/core.module';
import { Cov001AppRoutingModule } from './app-routing.module';
import { Cov001HomeModule } from './home/home.module';
import { Cov001EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Cov001SharedModule,
    Cov001CoreModule,
    Cov001HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Cov001EntityModule,
    Cov001AppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class Cov001AppModule {}
