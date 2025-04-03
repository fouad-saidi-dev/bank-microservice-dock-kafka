import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../models/customer.model";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }

  public addCustomer(customer:Customer):Observable<Customer> {
    return this.http.post<Customer>(`${environment.baseUrl}/customer-service/customers`,customer)
  }

  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(`${environment.baseUrl}/customer-service/customers`)
  }

  public getCustomer(id:number):Observable<Customer>{
    return this.http.get<Customer>(`${environment.baseUrl}/customer-service/customers/${id}`)
  }

  public editCustomer(customer:Customer,id:number):Observable<Customer> {
    return this.http.put<Customer>(`${environment.baseUrl}/customer-service/customers/${id}`,customer)
  }

  public deleteCustomer(id:number):Observable<void>{
    return this.http.get<void>(`${environment.baseUrl}/customer-service/customers/${id}`)
  }
}
