import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CustomerService} from "../../../services/customer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../../../models/customer.model";
import {AccountService} from "../../../services/account.service";
import {Account} from "../../../models/account.model";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrl: './add-account.component.css'
})
export class AddAccountComponent implements OnInit {
  accountForm!: FormGroup;
  accountId!:string;
  constructor(private formBuilder:FormBuilder,
              private accountService:AccountService,
              private activatedRoute:ActivatedRoute,
              private route:Router) {
  }
  ngOnInit(): void {
    this.accountId = this.activatedRoute.snapshot.params['id']

    this.accountId === undefined ? console.log("undefined") : console.log("not done");

    if (this.accountId != undefined)
      this.getAccount()

    this.accountForm = this.formBuilder.group({
      balance: this.formBuilder.control(''),
      accountType: this.formBuilder.control(''),
      customer: this.formBuilder.control('')
    })
  }

  getAccount():void{
    this.accountService.getAccount(this.accountId).subscribe({
      next:(data:Account)=>{
        console.log("Account: "+data)
        this.accountForm = this.formBuilder.group({
          balance: this.formBuilder.control(data.balance),
          accountType: this.formBuilder.control(data.accountType),
          customer: this.formBuilder.control(data.customer)
        })
      },
      error:(err)=>{
        console.log("Error: ",err)
      }
    })
  }


  saveAccount() {
    let account:Account = this.accountForm.value
    if(this.accountId===undefined){
      this.accountService.createAccount(account).subscribe({
        next:(data) => {
          console.log("Success create: "+data)
          this.route.navigateByUrl('/admin/accounts')
        },
        error:(err)=>{
          console.error("Error: ",err)
        }
      })
    } else {
      this.accountService.updateAccount(account,this.accountId).subscribe({
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
    this.accountForm.reset();
  }
}
