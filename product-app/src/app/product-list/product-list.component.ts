import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';  
import { Product } from '../product';  
import { Observable,Subject } from "rxjs";  
import {FormControl,FormGroup,Validators} from '@angular/forms'; 

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  constructor(private productservice:ProductService) { }

  productArray: any[] = [];  
  dtOptions: DataTables.Settings = {};  
  dtTrigger: Subject<any>= new Subject();  

  products: Observable<Product[]>;  
  product : Product=new Product();  
  deleteMessage=false;  
  productlist:any;  
  isupdated = false;    

  ngOnInit(): void {
    this.isupdated=false;  
    this.dtOptions = {  
      pageLength: 6,  
      stateSave:true,  
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],  
      processing: true  
    };     
    this.productservice.getProductList().subscribe(data =>{  
      this.products = data; 
      console.log(this.products);
      this.dtTrigger.next();  
    }); 
  }

  deleteProduct(id: number) {
    this.productservice.deleteProduct(id).subscribe(data => {
      console.log(data);
      this.deleteMessage = true;
      this.productservice.getProductList().subscribe(data => {
        this.products = data;
      })
    }, error => console.log(error));
  }

  updateProduct(id: number) {
    this.productservice.getProduct(id).subscribe(data => {
        this.productlist = data;
    }, error => console.log(error));
  }

  productupdateform = new FormGroup({
    product_id: new FormControl(),  
    product_name: new FormControl(),  
    product_price: new FormControl()
  });

  updatePro(updatepro) {
    this.product = new Product();
    this.product.productId = this.ProductId.value;
    this.product.productName=this.ProductName.value;  
    this.product.productPrice=this.ProductPrice.value;

    this.productservice.updateProduct(this.product.productId, this.product).subscribe(data => {
      this.isupdated = true;
      this.productservice.getProductList().subscribe(data => {
        this.products = data;
      });
    }, error => console.log(error));
  }

  get ProductId(){  
    return this.productupdateform.get('productId');  
  }  

  get ProductName() {
    return this.productupdateform.get('productName');
  }

  get ProductPrice() {
    return this.productupdateform.get('productPrice');
  }

  changeisUpdate(){  
    this.isupdated=false;  
  }
}
