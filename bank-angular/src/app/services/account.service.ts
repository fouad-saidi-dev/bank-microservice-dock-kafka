import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";
import {Account} from "../models/account.model";


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  public getAccounts():Observable<Array<Account>> {
    return this.http.get<Array<Account>>(`${environment.baseUrl}/account-service/accounts`);
  }

  public getAccount(id:string):Observable<Account> {
    return this.http.get<Account>(`${environment.baseUrl}/account-service/accounts/${id}`);
  }

  public createAccount(account:Account):Observable<Account> {
    return this.http.post<Account>(`${environment.baseUrl}/account-service/accounts`, account);
  }

  public updateAccount(account:Account,id:string):Observable<Account> {
    return this.http.put<Account>(`${environment.baseUrl}/account-service/accounts/${id}`, account);
  }

  public deleteAccount(id:string):Observable<void> {
    return this.http.delete<void>(`${environment.baseUrl}/account-service/accounts/${id}`);
  }
}
