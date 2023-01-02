import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

@Input() sideNavStatus:boolean = false;

  // list = [
  //   {
  //     number: '1',
  //     name: 'home',
  //     icon: 'fa-solid fa-house',
  //   },
  //   {
  //     number: '2',
  //     name: 'Products',
  //     icon: 'fa-solid fa-box',
  //   },
  //     {
  //     number: '3',
  //     name: 'Order',
  //     icon: 'fa-solid fa-cart-shopping',
  //   },
  //       {
  //     number: '4',
  //     name: 'Settings',
  //     icon: 'fa-solid fa-gear',
  //   },
  //         {
  //     number: '5',
  //     name: 'About',
  //     icon: 'fa-solid fa-circle-info',
  //   },
  //   {
  //     number: '6',
  //     name: 'Contact',
  //     icon: 'fa-solid fa-phone',
  //     }
  // ];

  constructor() { }

  ngOnInit(): void {
  }

}