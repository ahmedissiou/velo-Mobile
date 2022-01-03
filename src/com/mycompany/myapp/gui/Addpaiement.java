/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;

import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.entities.paiement;
import static com.mycompany.myapp.gui.PaiementOrder.isNotInteger;
import com.mycompany.myapp.services.coursService;
import com.mycompany.myapp.services.paiementService;


/**
 *
 * @author Fedi
 */
public class Addpaiement extends Form {
    
Form current;
    Form f;
    paiement r = new paiement();
    int cour_id;
  
   cours e = new cours();


 public Addpaiement(int cour_id) {
       this.cour_id = cour_id;
     
      current=this;
        setTitle("Reservation");
          // current.setUIID("formulaire");
       current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new coursForm().show();

         }
     });
       
       current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           HomeForm home = new HomeForm();
           home.getF().show();
            }
        });
        
        
         
         
       
           
            
            
        

        
           current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
                HomeForm hf = new HomeForm();
                hf.getF().show();
           
        });
           
            setLayout(BoxLayout.y());
        
            coursService se = new coursService();
            Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Label", 12);
         final FontImage bike = FontImage.createMaterial(FontImage.MATERIAL_DIRECTIONS_BIKE, "Label", 12);
    
       // c3.add(adr);
         // c3.add(bike);
         //  c3.add(cart);
        
        //e = se.getEvent2(event_id);
       
        current.add(c3);
        
        TextField nom = new TextField("","nom");
        TextField prenom = new TextField("", "Prénom");
        TextField mail= new TextField("", "mail");
      
        FloatingActionButton btnValider= FloatingActionButton.createFAB(FontImage.MATERIAL_CHECK );

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0)||(prenom.getText().length()==0))
                    Dialog.show("Alert", "Oups Champs vide !", new Command("OK"));
                 
                    else if ((!mail.getText().contains("@")) || (!mail.getText().contains("."))) {
            Dialog.show("Erreur", "Adresse Email non valide", "OK", null);}
                   
           
        
                else
                {
                   
                        paiement r = new paiement(nom.getText(), prenom.getText(),mail.getText());
                        if( paiementService.getInstance().addReservation(r,cour_id))
                           Dialog.show("Success"," ",new Command("OK"));
                      
                 PaiementOrder w = new PaiementOrder(e);
                    w.getF().show();

               } }  });
                
                
      
        
        current.addAll(nom,prenom,mail,btnValider);

        }
    

  
    
     
 

     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
}