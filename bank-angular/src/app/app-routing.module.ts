import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListCustomersComponent} from "./components/customers/list-customers/list-customers.component";
import {ListAccountsComponent} from "./components/accounts/list-accounts/list-accounts.component";
import {AddCustomerComponent} from "./components/customers/add-customer/add-customer.component";
import {AddAccountComponent} from "./components/accounts/add-account/add-account.component";
import {AppBarComponent} from "./components/app-bar/app-bar.component";
import {ListTransactionsComponent} from "./components/transactions/list-transactions/list-transactions.component";
import {AddTransactionComponent} from "./components/transactions/add-transaction/add-transaction.component";
import {AuthGuard} from "./guards/auth.guard";

const routes: Routes = [
  {path:'admin',component:AppBarComponent,children:[
      {path:'customers',component:ListCustomersComponent,canActivate:[AuthGuard]},
      {path:'add-customer',component:AddCustomerComponent},
      {path:'edit-customer/:id',component:AddCustomerComponent},
      {path:'accounts',component:ListAccountsComponent,canActivate:[AuthGuard]},
      {path:'add-account',component:AddAccountComponent},
      {path:'edit-account/:id',component:AddAccountComponent},
      {path:'transactions',component:ListTransactionsComponent,canActivate:[AuthGuard]},
      {path:'add-transaction',component:AddTransactionComponent},
    ]
  },
  {path:'',redirectTo:'/',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
