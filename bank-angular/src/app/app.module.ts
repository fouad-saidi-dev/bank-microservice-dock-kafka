import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAccountComponent } from './components/accounts/add-account/add-account.component';
import { ListAccountsComponent } from './components/accounts/list-accounts/list-accounts.component';
import { ListCustomersComponent } from './components/customers/list-customers/list-customers.component';
import { AddCustomerComponent } from './components/customers/add-customer/add-customer.component';
import { AppBarComponent } from './components/app-bar/app-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    AddAccountComponent,
    ListAccountsComponent,
    ListCustomersComponent,
    AddCustomerComponent,
    AppBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
