import {Component, OnInit, ViewChild} from '@angular/core';
import {AccountService} from "../../../services/account.service";
import {Router} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {KeycloakService} from "keycloak-angular";
import {TransactionDialogComponent} from "../transaction-dialog/transaction-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-list-accounts',
  templateUrl: './list-accounts.component.html',
  styleUrl: './list-accounts.component.css'
})
export class ListAccountsComponent implements OnInit{

  public accounts: any;
  public dataSource!: any;
  public displayedColumns: string[] = ['accountNumber', 'accountType', 'balance','customerName','transactions','actions'];

  constructor(private accountService:AccountService,
              private router:Router,
              public keycloakService:KeycloakService,
              private dialog:MatDialog) {
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  ngOnInit(): void {
    this.getAccounts().then(r => console.log(this.accounts));
  }

  async getAccounts() {
    this.accountService.getAccounts().subscribe({
      next: (data) => {
        console.log(data);
        this.accounts = data;
        this.dataSource = new MatTableDataSource(this.accounts);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  editAccount(id:number) {
     this.router.navigateByUrl(`/edit-account/${id}`);
  }

  deleteAccount(id:string) {
    this.accountService.deleteAccount(id).subscribe({
      next: (data) => {
        console.log(data);
        this.getAccounts().then(r => console.log(this.accounts));
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  displayTrxDialog(trxList:any[]) {
    console.log("Transfers",trxList)
    this.dialog.open(TransactionDialogComponent, {
      height: '400px',
      width: '800px',
      data : {
        trxList,
        // name,
        // id,
        // sourceRib
      }
    });
  }

}
