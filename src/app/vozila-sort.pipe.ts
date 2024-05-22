import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'vozilaSort'
})
export class VozilaSortPipe implements PipeTransform {

  transform(array: Array<any>, reg: string, fuel: string): Array<any> {
    if(reg != null || reg == ""){
      console.log("asd")
      return array.filter(singleItem =>
        singleItem.registracija.toLowerCase().includes(reg.toLowerCase()) && 
        singleItem.registracija.toLowerCase().includes(fuel.toLowerCase()))
    }
    return null;
  }

}
