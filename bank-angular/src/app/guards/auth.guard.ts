import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard extends KeycloakAuthGuard {
  constructor(
    protected override readonly router: Router,
    protected override readonly keycloakAngular: KeycloakService
  ) {
    super(router, keycloakAngular);
  }

  /**
   * Determines if the user can activate a route.
   * Optionally checks for specific roles.
   */
  public async isAccessAllowed(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    // Check if the user is authenticated
    if (!this.authenticated) {
      await this.keycloakAngular.login({
        redirectUri: window.location.origin + state.url,
      });
    }

    // Check for required roles (if any)
    const requiredRoles = route.data['roles'] || [];
    if (!this.hasRequiredRoles(requiredRoles)) {
      // Redirect if roles don't match
      return false;
    }

    return true;
  }

  /**
   * Checks if the user has all required roles.
   */
  private hasRequiredRoles(requiredRoles: string[]): boolean {
    if (!requiredRoles || requiredRoles.length === 0) {
      return true; // No roles required
    }
    return requiredRoles.every((role) => this.roles.includes(role));
  }
}
