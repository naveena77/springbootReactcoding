import { CalculatorService } from "./culculator.service";
import {TestBed} from '@angular/core/testing';
import { LoggerService } from "./logger.service";

describe('CalculatorService',() =>{

    let calculator:CalculatorService,
        loggerSpy:any;

    beforeEach(()=>{
        console.log("calling before each")
        loggerSpy = jasmine.createSpyObj('LoggerSerVice',["log"]);

        TestBed.configureTestingModule({
            providers:[
                CalculatorService,
                  {provide:LoggerService,useValue:loggerSpy}
            ]
        });
        calculator =TestBed.inject(CalculatorService);
    });
    
    it('should add the two numbers',() =>{  
        console.log("add test") ;
       const result = calculator.add(2,2);
       expect(result).toBe(4);
       expect(loggerSpy.log).toHaveBeenCalledTimes(1);
    });

    it('should subtract the two numbers',() =>{
        console.log("subtract test") ;
       const result = calculator.subtract(2,2);
       expect(result).toBe(0);
       expect(loggerSpy.log).toHaveBeenCalledTimes(1);
     });
})