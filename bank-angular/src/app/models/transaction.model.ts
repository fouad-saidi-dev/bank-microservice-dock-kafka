import {Account} from "./account.model";

export interface Transaction {
  id:number;
  amount:number;
  transactionType:string;
  transactionDate:Date;
  accountDetail:Account;
}
