import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  getUserId(): string {
    const user = this.getUser();
    if (user == null) return '';
    return user.id;
  }
 
  saveToken(token: string): void {
    if (this.isLocalStorageAvailable()) {
      localStorage.removeItem(TOKEN);
      localStorage.setItem(TOKEN, token);  
    }
  }
  
  saveUser(user: any): void {
    if (this.isLocalStorageAvailable()) {
      localStorage.removeItem(USER);
      localStorage.setItem(USER, JSON.stringify(user));
    }
  }

  getToken(): string {
    if (this.isLocalStorageAvailable()) {
      return localStorage.getItem(TOKEN);
    }
    return null;
  }

  getUser(): any {
    if (this.isLocalStorageAvailable()) {
      return JSON.parse(localStorage.getItem(USER));
    }
    return null;
  }

  getUserRole(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.role;
  }

  isAdminLoggedIn(): boolean {
    if (this.isLocalStorageAvailable() && this.getToken() !== null) {
      const role: string = this.getUserRole();
      return role === "ADMIN";
    }
    return false;
  }

  isCustomerLoggedIn(): boolean {
    if (this.isLocalStorageAvailable() && this.getToken() !== null) {
      const role: string = this.getUserRole();
      return role === "CUSTOMER";
    }
    return false;
  }

  signout(){
    if (this.isLocalStorageAvailable()) {
      localStorage.removeItem(USER);
      localStorage.removeItem(TOKEN);
    }
  }

  private isLocalStorageAvailable(): boolean {
    try {
      const testKey = '__testKey__';
      localStorage.setItem(testKey, testKey);
      localStorage.removeItem(testKey);
      return true;
    } catch (e) {
      return false;
    }
  }
}


/*//9-05-2024
import { Injectable } from '@angular/core';

const TOKEN = 'token'
const USER = 'user'

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  
  static getToken() {
    throw new Error('Method not implemented.');
  }
  constructor() { }

  saveToken(token: string): void {
    if (this.isLocalStorageAvailable()) {
      localStorage.removeItem(TOKEN);
      localStorage.setItem(TOKEN, token);  
    }
  }
  
  saveUser(user: any): void {
    if (this.isLocalStorageAvailable()) {
      localStorage.removeItem(USER);
      localStorage.setItem(USER, JSON.stringify(user));
    }
  }

  getToken(): string {
    if (this.isLocalStorageAvailable()) {
      return localStorage.getItem(TOKEN);
    }
    return null;
  }

  getUser(): any {
    if (this.isLocalStorageAvailable()) {
      return JSON.parse(localStorage.getItem(USER));
    }
    return null;
  }

  getUserRole(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.role;
  }

  isAdminLoggedIn(): boolean {
    if (this.isLocalStorageAvailable() && this.getToken() !== null) {
      const role: string = this.getUserRole();
      return role === "ADMIN";
    }
    return false;
  }

  isCustomerLoggedIn(): boolean {
    if (this.isLocalStorageAvailable() && this.getToken() !== null) {
      const role: string = this.getUserRole();
      return role === "CUSTOMER";
    }
    return false;
  }

  signout(){
    if (this.isLocalStorageAvailable()) {
      localStorage.removeItem(USER);
      localStorage.removeItem(TOKEN);
    }
  }

  private isLocalStorageAvailable(): boolean {
    try {
      const testKey = '__testKey__';
      localStorage.setItem(testKey, testKey);
      localStorage.removeItem(testKey);
      return true;
    } catch (e) {
      return false;
    }
  }
}

*/

/*import { Injectable } from '@angular/core';

const TOKEN = 'token'
const USER = 'user'

@Injectable({
  providedIn: 'root'
})

export class StorageService {
  constructor() { }

  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);  
  }
  
  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == "ADMIN";
  }

  static isCustomerLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == "CUSTOMER";
  }
}
*/
/*import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root'
})

export class StorageService {
  constructor() { }

  saveToken(token: string): void {
    localStorage.removeItem(TOKEN);
    localStorage.setItem(TOKEN, token);  
  }
  
  saveUser(user: any): void {
    localStorage.removeItem(USER);
    localStorage.setItem(USER, JSON.stringify(user));
  }

  getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  getUserRole(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.role;
  }

  isAdminLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === "ADMIN";
  }

  isCustomerLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === "CUSTOMER";
  }

  static signout(){
    window.localStorage.removeItem(USER);
    window.localStorage.removeItem(TOKEN);
  }
}
*/
