import { Component, OnInit } from '@angular/core';
import { CurrencyListService } from '../services/currencylistservice/currencylist.service';
import { Currency } from 'src/app/model/currency';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ConvertCurrencyService } from '../services/convertcurrencyservice/convertcurrencyservice';
import { CurrencyConverter } from 'src/app/model/currencyconverter';
import {MatSnackBar} from '@angular/material/snack-bar';
@Component({
  selector: 'app-currencyconverter',
  templateUrl: './currencyconverter.component.html',
  styleUrls: ['./currencyconverter.component.css']
})
export class CurrencyconverterComponent implements OnInit {
  error:string="";
  currencyList: Currency[]=[];
  currencyConverter: CurrencyConverter={
    sourceCurrency: '',
    targetCurrency: '',
    enteredAmountValue: 0
  };
  form: FormGroup;

  constructor(private fb: FormBuilder,
              private currencyListService: CurrencyListService,
              private convertCurrencyService:ConvertCurrencyService,
              private _snackBar: MatSnackBar) {
    this.form = fb.group({
      sourceCurrency: new FormControl<string>('', [
        Validators.minLength(3),
        Validators.required
      ]),
      targetCurrency: new FormControl<string>('', 
      [Validators.minLength(3), Validators.required]),
      enteredAmountValue: new FormControl<string>('',[Validators.required]),
      convertedAmount:new FormControl<string>(''),
    });
  }

  openSnackBar(message: string,action: string,className: string) {
    this._snackBar.open(message,action,{
      duration: 20000,
      verticalPosition: 'top',
      horizontalPosition: 'center',
      panelClass: [className],
    });
  }
  
  ngOnInit() {
     this.currencyListService.findAllCurrency()
      .subscribe({
      next:(currency) => 
      {
        this.currencyList=currency;
        console.log(this.currencyList);
      },
      error: (error) =>
      {
        if(error === 400){
          this.error=error.message;
          this.openSnackBar(this.error,"Cancel",'error-snackbar');
          console.log("Bad request",error.message);
        }else{
          this.error=error.message;
          this.openSnackBar(error.message,"Cancel",'error-snackbar');
          console.log("something went wrong",error.message);
        }
      }
   });
 }

 convertCurrency() {
  if (this.form.invalid) {
    return;
  }
  const val = this.form.value;
  this.currencyConverter ={
    sourceCurrency: val.sourceCurrency,
    targetCurrency: val.targetCurrency,
    enteredAmountValue: val. enteredAmountValue
  };  
  console.log(this.currencyConverter);    
  this.convertCurrencyService.convertCurrency(this.currencyConverter)
   .subscribe({
        next: (currency) => 
        {
          console.log("The converted Amount is:"+currency);
    this.form.controls['convertedAmount'].setValue(currency);
        },
        error: (error) =>
        {
          if(error.status === 400){
            this.error="Bad request, Please check the request";
            this.openSnackBar(this.error,"Cancel",'error-snackbar');
            console.log("Bad request",error.message);
          }else{
            this.openSnackBar(error.message,"Cancel",'error-snackbar');
            this.error=error.message;
            console.log("something went wrong",error);
          }
        }
     });
  }

  reset(){
    this.form.reset();
  }

 
}
