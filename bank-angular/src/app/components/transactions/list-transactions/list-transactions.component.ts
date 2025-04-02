import {Component, OnInit, ViewChild} from '@angular/core';
import {TransactionService} from "../../../services/transaction.service";
import {Router} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-list-transactions',
  templateUrl: './list-transactions.component.html',
  styleUrl: './list-transactions.component.css'
})
export class ListTransactionsComponent implements OnInit{

  public transactions: any;
  public dataSource!: any;
  public displayedColumns: string[] = ['id', 'amount', 'transactionType','transactionDate'];

  constructor(private transactionService:TransactionService,private route:Router) {
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  ngOnInit(): void {
    this.getTransactions().then(r => console.log(this.transactions));
  }

  async getTransactions() {
    this.transactionService.getTransactions().subscribe({
      next: (data) => {
        console.log(data);
        this.transactions = data;
        this.dataSource = new MatTableDataSource(this.transactions);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  editCustomer(id:number) {
    this.route.navigateByUrl(`/edit-customer/${id}`);
  }

  deleteCustomer(id:number) {
    this.transactionService.deleteTransaction(id).subscribe({
      next: (data) => {
        console.log(data);
        this.getTransactions().then(r => console.log(this.transactions));
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

}
