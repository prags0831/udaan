import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from 'express';
import * as $ from "jquery";
import { FlightService } from '../_services/flight.service';

@Component({
  selector: 'app-seatmatrix',
  templateUrl: './seatmatrix.component.html',
  styleUrls: ['./seatmatrix.component.css']
})
export class SeatmatrixComponent implements OnInit {

  objects=[1,2,3,4,5,6]
  totalPrice=0;

  selectedSeat: string = '';
  selectedFood: string = '';
  basicseatsA = [
    {

      "seatno": "1A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "2A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "3A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "4A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "5A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "6A",
      "price": 500,
      "booked": false
    }
  ]

  basicseatsD = [
    {
      "seatno": "7A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "8A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "9A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "10A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "11A",
      "price": 500,
      "booked": false
    },
    {
      "seatno": "12A",
      "price": 500,
      "booked": false
    }
  ]
  basicseatsB = [
    {
      "seatno": "1B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "2B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "3B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "4B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "5B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "6B",
      "price": 300,
      "booked": false
    }
  ]

  basicseatsC = [
    {
      "seatno": "7B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "8B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "9B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "10B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "11B",
      "price": 300,
      "booked": false
    },
    {
      "seatno": "12B",
      "price": 300,
      "booked": false
    }
  ]

  itemsA =
    [{
      "itemname": "Salad",
      "price": 750,
    }
    ]

  itemsB = [
    {
      "itemname": "Mini Combo",
      "price": 1000
    }
  ]

  itemsC = [
    {
      "itemname": "King Combo",
      "price": 1500
    }
  ]

  foodChangeHandler(event: any) {
    this.selectedFood = event.target.value
  }

  radioChangeHandler(event: any) {
    this.selectedSeat = event.target.value
  }

  constructor(private https: HttpClient, private service: FlightService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    var $window = $(window);
    $('input:checkbox').filter(':checked');
    alert("You can check only one seat");

    this.choose();
  }
  choose() {

    if (document.getElementById("salad")) {
      console.log("You selected salad only")
      var saladprice = 650;
      console.log(saladprice)
    }
    else if (document.getElementById("minicombo")) {
      console.log("You selected minicombo only")
      var minicomboprice = 650;
      console.log(minicomboprice)
    }
  }
  chooseSeat() {
    $(document).ready(function () {
      $('input[type="radio"]').click(function () {
        var inputValue = $(this).attr("value");
        var targetBox = $("." + inputValue);
        $(".selectt").not(targetBox).hide();
        $(targetBox).show();
      });
    });
  }
}

