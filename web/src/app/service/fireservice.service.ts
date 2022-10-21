import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFirestore,
  AngularFirestoreDocument,
} from '@angular/fire/compat/firestore';
import { Router } from '@angular/router';
import firebase from 'firebase/compat/app';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class FireserviceService {
  userData: any;

  constructor(
    public afauth: AngularFireAuth,
    public store: AngularFirestore,
    public router: Router
  ) { 
    this.afauth.authState.subscribe((user) => {
      if (user) {
        this.userData = user;
        JSON.parse(localStorage.getItem('user')!);
        localStorage.setItem('user', JSON.stringify(this.userData));
      } else {
        JSON.parse(localStorage.getItem('user')!);
        localStorage.setItem('user', 'null');
      }
    });
  }

  async loginGoogle() {
    try {
      return await this.afauth
        .signInWithPopup(new firebase.auth.GoogleAuthProvider())       
    } catch (error) {
      return null;
    }
  }

  async logout() {
    try{
      return await this.afauth
      .signOut();
    }catch (error) {
      return error;
    }
  }

  getUserLogged() {
    return this.afauth.authState;
  }

  SetUserData(user: any) {
    const userRef: AngularFirestoreDocument<any> = this.store.doc(
      `users/${user.uid}`
    );
    const userData: User = {
      uid: user.uid,
      email: user.email,
      displayName: user.displayName,
      photoURL: user.photoURL,
      emailVerified: user.emailVerified,
    };
    return userRef.set(userData, {
      merge: true,
    });
  }
  
}

