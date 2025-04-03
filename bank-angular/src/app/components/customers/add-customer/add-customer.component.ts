import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CustomerService} from "../../../services/customer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../../../models/customer.model";

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrl: './add-customer.component.css'
})
export class AddCustomerComponent implements OnInit{

  customerForm!: FormGroup;
  customerId!:number;
  constructor(private formBuilder:FormBuilder,
              private customerService:CustomerService,
              private activatedRoute:ActivatedRoute,
              private route:Router) {
  }
  ngOnInit(): void {
    this.customerId = this.activatedRoute.snapshot.params['id']

    this.customerId === undefined ? console.log("undefined") : console.log("not done");

    if (this.customerId != undefined)
      this.getCustomer()

    this.customerForm = this.formBuilder.group({
      firstName: this.formBuilder.control(''),
      lastName: this.formBuilder.control(''),
      email: this.formBuilder.control('')
    })
  }

  getCustomer():void{
    this.customerService.getCustomer(this.customerId).subscribe({
      next:(data:Customer)=>{
        console.log("Customer: "+data)
        this.customerForm = this.formBuilder.group({
          firstName: this.formBuilder.control(data.firstName),
          lastName: this.formBuilder.control(data.lastName),
          email: this.formBuilder.control(data.email)
        })
      },
      error:(err)=>{
        console.log("Error: ",err)
      }
    })
  }


  saveCustomer() {
    let customer:Customer = this.customerForm.value
    if(this.customerId===undefined){
      this.customerService.addCustomer(customer).subscribe({
        next:(data) => {
          console.log("Success create: "+data)
          this.route.navigateByUrl('/admin/customers')
        },
        error:(err)=>{
          console.error("Error: ",err)
        }
      })
    } else {
      this.customerService.editCustomer(customer,this.customerId).subscribe({
        next:(data) => {
          console.log("Success update: "+data)
        },
        error:(err)=>{
          console.error("Error update: ",err)
        }
      })
    }

  }

  onCancel() {
    this.customerForm.reset();
  }

}
