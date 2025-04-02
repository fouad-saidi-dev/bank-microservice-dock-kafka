import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";
import {Transaction} from "../models/transaction.model";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http:HttpClient) { }

  public addTransaction(transaction:Transaction):Observable<Transaction> {
    return this.http.post<Transaction>(`${environment.baseUrl}/account-service/transactions`,transaction)
  }

  public getTransactions():Observable<Array<Transaction>>{
    return this.http.get<Array<Transaction>>(`${environment.baseUrl}/account-service/transactions`)
  }

  public getTransaction(id:number):Observable<Transaction>{
    return this.http.get<Transaction>(`${environment.baseUrl}/account-service/transactions/${id}`)
  }

  public editTransaction(transaction:Transaction,id:number):Observable<Transaction> {
    return this.http.put<Transaction>(`${environment.baseUrl}/account-service/transactions/${id}`,transaction)
  }

  public deleteTransaction(id:number):Observable<void>{
    return this.http.get<void>(`${environment.baseUrl}/account-service/transactions/${id}`)
  }
}
