import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';  
import {FormControl,FormGroup,Validators} from '@angular/forms';  
import { Product } from '../product';  
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  constructor(private productservice:ProductService, private router: Router, ) { }

  product: Product = new Product();
  submitted = false;

  ngOnInit(): void {
    this.submitted=false;
  }

  productsaveform = new FormGroup({
    productName:new FormControl('' , [Validators.required, Validators.minLength(5)]),
    productPrice:new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")])  
  });

  saveProduct(saveProduct) {
    this.product = new Product();
    this.product.productName = this.ProductName.value;
    this.product.productPrice = this.ProductPrice.value;
    this.submitted = true;  
    this.save();  
  }

  save() {
    this.productservice.createProduct(this.product).subscribe(data => console.log(data), error => console.log(error));
   // this.router.navigate(['/products']);
    this.product = new Product();
  }

  get ProductName() {
    return this.productsaveform.get('productName');
  }

  get ProductPrice() {
    return this.productsaveform.get('productPrice');
  }

  addProductForm() {
    this.submitted =  false;
    this.productsaveform.reset();
  }
}
