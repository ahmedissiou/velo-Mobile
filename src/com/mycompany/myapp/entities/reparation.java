/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author asus
 */
public class reparation {
    	
    private int    id;
    
    private Date dateRep;
    
    private String descriptionprob;
	
    private String type;
   
    private String marque;
   
    private String ville;
    
    
   

   
   
    
   
  

    public reparation() {
    }
      public reparation(Date dateRep,String descriptionprob,String type,String marque, String ville) {
        
        this.dateRep = dateRep;
        this.descriptionprob = descriptionprob;
        this.type = type;
        this.marque = marque;
        this.ville = ville;

       
    }
    
    public reparation(Integer id,Date dateRep,String descriptionprob,String type,String marque, String ville) {
        
        this.id = id;
        this.dateRep=dateRep;
        this.descriptionprob = descriptionprob;
        this.type = type;
        this.marque = marque;
        this.ville = ville;

       
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      public  Date getDateRep() {
        return dateRep;
    }

    public void setDateRep(Date dateRep) {
        this.dateRep = dateRep;
    }
    

    public String getDescriptionprob() {
        return descriptionprob;
    }

    public void setDescriptionprob(String descriptionprob) {
        this.descriptionprob = descriptionprob;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "reparation{" + "id=" + id + ", descriptionprob=" + descriptionprob + ", type=" + type + ", marque=" + marque + ", ville=" + ville + '}';
    }

    
    
   
    
}