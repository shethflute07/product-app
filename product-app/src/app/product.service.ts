import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';  
import { Observable } from 'rxjs';  

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/';

  constructor(private http:HttpClient) { }

  getProductList(): Observable<any> { 
    const headers = new HttpHeaders()
      .append('Access-Control-Allow-Origin', '*')
      .append('Authorization', 'Basic YmFuc2kwNzpiYW5zaTA3');
    return this.http.get(`${this.baseUrl}`+'products-list', {headers});  
  }  

  createProduct(product: object): Observable<object> {  
    const headers = new HttpHeaders()
      .append('Access-Control-Allow-Origin', '*')
      .append('Authorization', 'Basic YmFuc2kwNzpiYW5zaTA3');
    return this.http.post(`${this.baseUrl}`+'save-product', product, {headers});  
  }  

  deleteProduct(id: number): Observable<any> {  
    const headers = new HttpHeaders()
      .append('Access-Control-Allow-Origin', '*')
      .append('Authorization', 'Basic YmFuc2kwNzpiYW5zaTA3');
    return this.http.delete(`${this.baseUrl}`+`delete-product/`+`${id}`, {headers,  responseType: 'text' });  
  }  

  getProduct(id: number): Observable<Object> {  
    const headers = new HttpHeaders()
      .append('Access-Control-Allow-Origin', '*')
      .append('Authorization', 'Basic YmFuc2kwNzpiYW5zaTA3');
    return this.http.get(`${this.baseUrl}`+`product/`+`${id}`, {headers});  
  }  

  updateProduct(id: number, value: any): Observable<Object> {  
    const headers = new HttpHeaders()
      .append('Access-Control-Allow-Origin', '*')
      .append('Authorization', 'Basic YmFuc2kwNzpiYW5zaTA3');
    return this.http.post(`${this.baseUrl}`+`update-product/`+`${id}`, value, {headers});  
  }  
}
