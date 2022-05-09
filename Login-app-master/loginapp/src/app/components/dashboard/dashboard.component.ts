import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  welcomeMsg:string='';
  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }
  getWelcomeMsg(){
  this.userService.getWelcome().subscribe(
    (success:any)=>{
      // console.log("Getting error response");
      this.welcomeMsg=success;
      console.log(this.welcomeMsg)
    },
    (error) => {
      alert("Something went wrong");
      console.log(error);
    }
  )
  }

}
