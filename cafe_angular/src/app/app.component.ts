import { Component } from '@angular/core';
import { StorageService } from './auth-services/storage-service/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})

export class AppComponent {
  title = 'restaurant_angular';

  isAdminLoggedIn: boolean;
  isCustomerLoggedIn: boolean;

  constructor(private router: Router, private storageService: StorageService) { }

  ngOnInit() {
    this.updateLoginStatus();
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.updateLoginStatus();
      }
    });
  }

  logout() {
    this.storageService.signout(); // Call signout method on the instance
    this.router.navigateByUrl("/login");
  }

  private updateLoginStatus() {
    this.isAdminLoggedIn = this.storageService.isAdminLoggedIn();
    this.isCustomerLoggedIn = this.storageService.isCustomerLoggedIn();
  }
}
