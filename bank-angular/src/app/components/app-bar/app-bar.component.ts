import {Component, OnInit} from '@angular/core';
import {KeycloakProfile} from "keycloak-js";
import {KeycloakService} from "keycloak-angular";
import {NotificationService} from "../../services/notification.service";

@Component({
  selector: 'app-app-bar',
  templateUrl: './app-bar.component.html',
  styleUrl: './app-bar.component.css'
})
export class AppBarComponent implements OnInit{
  public profile!:KeycloakProfile;
  notificationCount= 0;
  notifications: any;
  constructor(public keycloakService:KeycloakService,private notificationService:NotificationService) {
  }
  ngOnInit(): void {
    if (this.keycloakService.isLoggedIn()){
      this.keycloakService.loadUserProfile().then(
        (profile) => {
          this.profile=profile;
        }
      )
    }

    this.countNotifications()
  }

  countNotifications(){
    this.notificationService.getNotifications().subscribe(data => {
      this.notifications=data
      this.notificationCount = data.length;
    })
  }

  async handleLogin() {
    await this.keycloakService.login({
      redirectUri: window.location.origin + 'admin/customers'
    });
  }
  handleLogout() {
    this.keycloakService.logout(window.location.origin);
  }
}
