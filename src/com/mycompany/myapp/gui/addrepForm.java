/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.codename1.ui.spinner.Picker;

import com.mycompany.myapp.entities.reparation;
import com.mycompany.myapp.services.servicereparation;



/**
 *
 * @author Fedi
 */
public class addrepForm extends Form {
    
Form current;
    Form f;
   reparation r = new reparation();
    

 public addrepForm(reparation r) {
     
    
      current=this;
        setTitle("Reservation");
       current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new repForm().show();

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
            setUIID("formss");
             
        
            servicereparation re = new servicereparation();
            Container c3 = new Container(BoxLayout.x());
       // final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
       // c3.add(adr);
        
        //e = se.getEvent2(event_id);
       
        current.add(c3);
        //TextField dateRep=new TextField("","dateRep");
       // TextField dateRep = new TextField("","dateRep");
        Picker pck = new Picker();
        TextField descriptionprob = new TextField("","description prob");
        TextField marque = new TextField("", "marque");
        TextField type= new TextField("", "type");
        TextField ville= new TextField("", "ville");
        
        Button btnValider = new Button("valider");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((descriptionprob.getText().length()==0)||(marque.getText().length()==0)||(type.getText().length()==0)||(ville.getText().length()==0))
                    Dialog.show("Alert", "Oups Champs vide !", new Command("OK"));
                else
                {
                    
                        reparation r = new reparation(pck.getDate(),descriptionprob.getText(), marque.getText(),type.getText(),ville.getText());
                      if( servicereparation.getInstance().addreparation(r))
                            Dialog.show("Success"," reparation ajouté ",new Command("OK"));
                            

                 repForm w = new repForm();
                    w.getF().show();

                } }  });
        
                        
                    
                
                       
                       
                   
                
                
                
        
        
        current.addAll(pck,descriptionprob,marque,type,ville,btnValider);

        }
    

  
    
     
 

     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
}