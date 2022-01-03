/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.fos_user;

/**
 *
 * @author asus
 */
public class Session {
    
  
        
  private static Session instance;
    private fos_user currUser;
    private static int idUser;

    public fos_user getCurrUser() {

        return currUser;
    }

    public void setCurrUser(fos_user currUser) {
        this.currUser = currUser;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    public static int getCurrentSession() throws Exception {
      // if (idUser != -1) {
          idUser=MyApplication.user.getId();
            return idUser;
    //   } else {
        //  throw new Exception("Session has not started yet!");
       // }
    }
    
}
