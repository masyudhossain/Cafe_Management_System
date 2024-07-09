import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../../auth-services/storage-service/storage.service';
import { Observable } from 'rxjs';


const BASIC_URL = 'http://localhost:8081/';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) { }

  getAllCategories(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + 'api/customer/categories', {
      headers: this.createAuthorizationHeader()
    })
  }

  getCategoriesByName(title: string): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/customer/categories/${title}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  getProductsByCategory(categoryId: number): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/customer/${categoryId}/products`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  getProductsByCategoryAndTitle(categoryId: number, title: string): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/customer/${categoryId}/product/${title}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  // Reservation Operations
  postReservation(reservationDto: any): Observable<any> {
    reservationDto.customerId = this.storageService.getUserId();
    console.log('Reservation Data:', reservationDto); // Log the data to check if it's correct
    return this.http.post<[]>(BASIC_URL + `api/customer/reservation`, reservationDto,
      {
        headers: this.createAuthorizationHeader()
      }
    );
  }

  getReservationsByUser(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/customer/reservations/${this.storageService.getUserId()}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }
  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    // Call getToken() on the instance of StorageService
    return authHeaders.set(
      'Authorization',
      'Bearer ' + this.storageService.getToken()
    );
  }
}
