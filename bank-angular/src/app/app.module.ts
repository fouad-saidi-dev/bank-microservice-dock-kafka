import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAccountComponent } from './components/accounts/add-account/add-account.component';
import { ListAccountsComponent } from './components/accounts/list-accounts/list-accounts.component';
import { ListCustomersComponent } from './components/customers/list-customers/list-customers.component';
import { AddCustomerComponent } from './components/customers/add-customer/add-customer.component';
import { AppBarComponent } from './components/app-bar/app-bar.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatDrawerContainer, MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatDialogModule} from "@angular/material/dialog";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {MatGridListModule} from "@angular/material/grid-list";
import { ListTransactionsComponent } from './components/transactions/list-transactions/list-transactions.component';
import { AddTransactionComponent } from './components/transactions/add-transaction/add-transaction.component';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MatBadge} from "@angular/material/badge";
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:9090',
        realm: 'bank-app',
        clientId: 'bank-cloud-app',
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    AddAccountComponent,
    ListAccountsComponent,
    ListCustomersComponent,
    AddCustomerComponent,
    AppBarComponent,
    ListTransactionsComponent,
    AddTransactionComponent,
    UnauthorizedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatDialogModule,
    MatProgressSpinner,
    MatGridListModule,
    KeycloakAngularModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    MatBadge
  ],
  providers: [
    //provideAnimationsAsync(),
    {provide: APP_INITIALIZER, useFactory: initializeKeycloak, multi: true, deps: [KeycloakService]},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
