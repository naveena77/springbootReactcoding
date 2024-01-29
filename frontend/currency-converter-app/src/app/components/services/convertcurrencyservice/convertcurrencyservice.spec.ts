import { TestBed } from "@angular/core/testing";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { CURRENCY } from "server/db-data";
import { CurrencyConverter } from "src/app/model/currencyconverter";
import { ConvertCurrencyService } from "./convertcurrencyservice";


describe("ConvertCurrencyService",() =>{
    let convertCurrencyService:ConvertCurrencyService,
            httpTestingController:HttpTestingController;

    beforeEach(()=>{
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers:[
                ConvertCurrencyService,
                HttpClient
            ]
        });
        convertCurrencyService = TestBed.inject(ConvertCurrencyService);
        httpTestingController = TestBed.inject(HttpTestingController);
    });

     it('should convert given currency',() =>{
        const currencyConverterObject:CurrencyConverter= {
            sourceCurrency: "EUR",
            targetCurrency: "USD",
            enteredAmountValue: 10
        }
        let expetedResult:any=10.83;
       convertCurrencyService.convertCurrency(currencyConverterObject)
             .subscribe(res =>{
                expect(res).toBe(expetedResult)
             })    
           const req = httpTestingController.expectOne("http://localhost:8080/api/currency/converter"); 
           expect(req.request.method).toEqual("POST");
           expect(req.request.body.sourceCurrency).toEqual(currencyConverterObject.sourceCurrency);
           req.flush(10.83);
       });

       it('should give an error if converion fails',() =>{
        const currencyConverterObject:CurrencyConverter= {
            sourceCurrency: "EUR",
            targetCurrency: "USD",
            enteredAmountValue: 10
        }
       convertCurrencyService.convertCurrency(currencyConverterObject)
             .subscribe(
                () => fail("the convertion opertion must have failed"),
                
                (error:HttpErrorResponse) =>
                       expect(error.status).toBe(500)
                );

             const req = httpTestingController.expectOne("http://localhost:8080/api/currency/converter")
             expect(req.request.method).toEqual("POST");
             req.flush('Convertion failed',{status:500,statusText:'Internal Server'});
       })

       it('should give an bad request error if users sends 0',() =>{
        const currencyConverterObject:CurrencyConverter= {
            sourceCurrency: "EUR",
            targetCurrency: "USD",
            enteredAmountValue: 10
        }
       convertCurrencyService.convertCurrency(currencyConverterObject)
            .subscribe(
                () => fail("the convertion opertion must have failed"),
                
                (error:HttpErrorResponse) =>
                    expect(error.status).toBe(400)
                );
             const req = httpTestingController.expectOne("http://localhost:8080/api/currency/converter")
             expect(req.request.method).toEqual("POST");
             req.flush('Convertion failed',{status:400,statusText:'Bad request'});
       })

      afterEach(() =>{
            httpTestingController.verify();
      })
    

})