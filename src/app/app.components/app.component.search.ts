import { Component } from '@angular/core';
import { Product } from '../app.models/product';
import { ProductsService } from '../app.request/productsService';
@Component({
  selector: 'search',
  template: `
    <h1>{{title}}</h1>
    
  `,
  providers: [ProductsService]
})
export class Search{
  title = 'Search Product By Name';
}
