import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Notification} from "../models/notification.model";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http:HttpClient) { }

  public getNotifications():Observable<Array<Notification>>{
     return this.http.get<Array<Notification>>(`${environment.baseUrl}/notification-service/notifications`)
  }
}
