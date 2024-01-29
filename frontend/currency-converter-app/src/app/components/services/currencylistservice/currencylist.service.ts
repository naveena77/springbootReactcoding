import {Injectable} from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpParams} from "@angular/common/http";
import {map} from "rxjs/operators";
import { Currency } from 'src/app/model/currency';
@Injectable({
  providedIn: 'root'
})
export class CurrencyListService {



constructor(private http:HttpClient) {

}
  findAllCurrency(): Observable<Currency[]> {
        return this.http.get<Currency[]>('http://localhost:8080/api/fetch/currency')
   }
 
}