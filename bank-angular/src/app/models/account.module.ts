interface Account {
  id:string;
  accountNumber:string;
  accountType:string;
  balance:number;
  customer:Customer;
  transactions:Transaction[];
}
