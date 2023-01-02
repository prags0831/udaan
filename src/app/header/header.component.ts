import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { User } from '../_services/user';
import { UserService } from '../_services/user.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  // user!:User
 
@Output() sidebarToggled = new EventEmitter<boolean>();
menuStatus:boolean = false;
user:any=null;
  constructor(private userService:UserService) { }
  ngOnInit(): void {
  // var x=this.user.userFirstName
this.user=this.userService.forUser();
  }

  SidebarToggle() {
    this.menuStatus = !this.menuStatus;
    this.sidebarToggled.emit(this.menuStatus);
  }
}