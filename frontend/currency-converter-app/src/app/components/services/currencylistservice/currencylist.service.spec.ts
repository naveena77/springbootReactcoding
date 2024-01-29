import { TestBed } from "@angular/core/testing";
import { CurrencyListService } from "./currencylist.service"
import { HttpClient } from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { CURRENCY } from "server/db-data";


describe("CurrecnyListService",() =>{
    let currecnyListService:CurrencyListService,
            httpTestingController:HttpTestingController;

    beforeEach(()=>{
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers:[
                CurrencyListService,
                HttpClient
            ]
        });
        currecnyListService = TestBed.inject(CurrencyListService);
        httpTestingController = TestBed.inject(HttpTestingController);
    })
     it('should retrive all currency',() =>{
          currecnyListService.findAllCurrency()
               .subscribe(currencies => {
                    console.log(currencies);
                    expect(currencies).toBeTruthy('');

                    expect(currencies.length).toBe(4,"incorrect number of currencies");

                    const currency = currencies.find(currency => currency.id=1);
                    
                    expect(currency?.currencyIdentifier).toBe("USD");
               });

               const req = httpTestingController.expectOne('http://localhost:8080/api/fetch/currency');
               expect(req.request.method).toEqual('GET'); 
               req.flush(CURRENCY);
               
     })


      afterEach(() =>{
            httpTestingController.verify();
      })
    

})