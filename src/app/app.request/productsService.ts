import { Injectable } from '@angular/core';
import { Product } from '../app.models/product';
@Injectable()
export class ProductService {
  getProducts(): Promise<Product[]> {
    return Promise.resolve(HEROES);
  }
}
