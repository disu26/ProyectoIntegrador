import { Component, OnInit } from '@angular/core';
import { 
  faUserGear, 
  faUser, 
  faQuestion, 
  faFolderPlus, 
  faHeartCirclePlus, 
  faArrowRightToBracket,
  faArrowRightFromBracket, 
  faBolt
} from '@fortawesome/free-solid-svg-icons';

import { Router } from '@angular/router';
import { FireserviceService } from 'src/app/service/fireservice.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userLogged = this.afAuth.getUserLogged();
  disabled: boolean = false;
  userIcon:String | null = '';

  faUserGear = faUserGear;
  faUser = faUser;
  faQuestion = faQuestion;
  faFolderPlus = faFolderPlus;
  faHeartCirclePlus = faHeartCirclePlus;
  faArrowRightToBracket = faArrowRightToBracket;
  faArrowRightFromBracket = faArrowRightFromBracket;
  faBolt = faBolt;


  constructor(public afAuth: FireserviceService, private route: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.traerDatos();
  }

  traerDatos() {
    this.userLogged.subscribe((value) => {    
      if (value?.email == undefined) {
        this.disabled = true;        
      } else {
        this.userIcon = value.photoURL;
        this.disabled = false;       
      }
    });
  }

  userInfo() {
    console.log(this.afAuth.userData.photoURL)
  }

  loginGoogle(){
    this.afAuth
      .loginGoogle()
      .then((res) => {
        if(res) {
          this.toastr.success('Login correcto');
          setTimeout(() => {
            this.route.navigate(['projects']);
          }, 3000);

        }else {
          this.toastr.error('Rectifique sus datos');
        }
      })
  }

  logout() {
    this.afAuth.logout();
    window.location.reload();
    this.route.navigate(['welcome']);
  }

}