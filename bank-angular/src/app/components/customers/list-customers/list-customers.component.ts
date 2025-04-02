import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {CustomerService} from "../../../services/customer.service";

@Component({
  selector: 'app-list-customers',
  templateUrl: './list-customers.component.html',
  styleUrl: './list-customers.component.css'
})
export class ListCustomersComponent implements OnInit{
  public customers: any;
  public dataSource!: any;
  public displayedColumns: string[] = ['id', 'firstName', 'lastName','email'];

  constructor(private customerService:CustomerService,
              private router:Router) {
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  ngOnInit(): void {
    this.getCustomers().then(r => console.log(this.customers));
  }

  async getCustomers() {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        console.log(data);
        this.customers = data;
        this.dataSource = new MatTableDataSource(this.customers);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  editCustomer(id:number) {
    this.router.navigateByUrl(`/edit-customer/${id}`);
  }

  deleteCustomer(id:number) {
    this.customerService.deleteCustomer(id).subscribe({
      next: (data) => {
        console.log(data);
        this.getCustomers().then(r => console.log(this.customers));
      },
      error: (err) => {
        console.log(err);
      }
    })
  }
}
