import {Customer} from "./customer.model";
import {Transaction} from "./transaction.model";

export interface Account {
  id:string;
  accountNumber:string;
  accountType:string;
  balance:number;
  customer:Customer;
  customerId:number;
  transactions:Transaction[];
}
