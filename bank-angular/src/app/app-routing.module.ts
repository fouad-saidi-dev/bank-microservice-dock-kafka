import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListCustomersComponent} from "./components/customers/list-customers/list-customers.component";
import {ListAccountsComponent} from "./components/accounts/list-accounts/list-accounts.component";
import {AddCustomerComponent} from "./components/customers/add-customer/add-customer.component";
import {AddAccountComponent} from "./components/accounts/add-account/add-account.component";

const routes: Routes = [
  {path:'customers',component:ListCustomersComponent},
  {path:'add-customer',component:AddCustomerComponent},
  {path:'edit-customer/:id',component:AddCustomerComponent},
  {path:'accounts',component:ListAccountsComponent},
  {path:'add-account',component:AddAccountComponent},
  {path:'edit-account/:id',component:AddAccountComponent},
  {path:'',redirectTo:'customers',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
