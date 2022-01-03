/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class paiement{
    
       private String nom;
     private String prénom;
     private Integer age;
       private String mail;
    
    private Integer id;
    
  
  
   
    private Integer note;
    private Integer user_id;
    
    private Integer cour_id;

   
      public paiement() {
    }

    public paiement(String nom, String prénom, Integer age) {
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
    }

    public paiement(String nom, String prénom, Integer age, Integer cour_id) {
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.cour_id = cour_id;
    }

   

    public paiement(String nom, String prénom, Integer age, Integer cour_id,Integer user_id) {
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }

    public paiement(String nom, String prénom, String mail) {
        this.nom = nom;
        this.prénom = prénom;
        this.mail = mail;
    }

    public paiement(String nom, String prénom, String mail, Integer user_id) {
        this.nom = nom;
        this.prénom = prénom;
        this.mail = mail;
        this.user_id = user_id;
    }
      
    
     
  

   
    
 
    public paiement(Integer id, Integer num_carte, Integer cvc, String type_car, String nom, String prénom, Integer age, Integer note, Integer user_id, Integer cour_id) {
        this.id = id;
     
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.note = note;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }

    public paiement(Integer id, String nom, String prénom, Integer age, Integer user_id, Integer cour_id) {
        this.id = id;
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }
      
         
    
  
    
 public paiement( Integer note) {
        
        this.note = note;}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCour_id() {
        return cour_id;
    }

    public void setCour_id(Integer cour_id) {
        this.cour_id = cour_id;
    }

    @Override
    public String toString() {
        return "paiement{" + "nom=" + nom + ", pr\u00e9nom=" + prénom + ", age=" + age + ", mail=" + mail + ", id=" + id + ", note=" + note + ", user_id=" + user_id + ", cour_id=" + cour_id + '}';
    }

   

}
