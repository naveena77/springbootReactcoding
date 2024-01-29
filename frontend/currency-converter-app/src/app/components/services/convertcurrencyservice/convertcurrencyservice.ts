import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import { CurrencyConverter } from "src/app/model/currencyconverter";

@Injectable()
export class ConvertCurrencyService {

    constructor(private http:HttpClient) {

    }
    convertCurrency(currencyConverter: CurrencyConverter): Observable<CurrencyConverter> {
      let headers = new HttpHeaders ({
            'Content-Type': 'application/json'
          });
          return this.http.post<any>("http://localhost:8080/api/currency/converter",
            currencyConverter, { headers: headers }
          )
    }
}