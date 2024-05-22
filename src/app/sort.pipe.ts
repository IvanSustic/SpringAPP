import { Pipe, PipeTransform } from '@angular/core';
import { Vozilo } from './vozilo';

@Pipe({
  name: 'sort'
})
export class SortPipe implements PipeTransform {

  transform(array: Array<any>, reg: string, fuel: string): Array<any> {
    return null;
  }
  

}
