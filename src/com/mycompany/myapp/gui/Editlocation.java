/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.locationn;
import com.mycompany.myapp.services.ServiceLocation;

/**
 *
 * @author Hajer
 */
public class Editlocation extends Form{ 
      Form  current;
        int id_velo;
        public int location_id;
     
    public Editlocation(int id) {
        this.location_id=id;
        current=this;
        setTitle("Editer");

         
        setLayout(BoxLayout.y());
        
        current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                  new HomeForm().show();

            }
        });
            
        
      setTitle("Ajouter votre location");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","nomlocation");
      //PickerComponent date = PickerComponent.createDate(new Date()).label("Date debut");
       // PickerComponent date1 = PickerComponent.createDate(new Date()).label("Date fin");
       Picker debut = new Picker();
debut.setType(Display.PICKER_TYPE_DATE);
    Picker fin = new Picker();
fin.setType(Display.PICKER_TYPE_DATE);
        Button btnValider = new Button("editer");
         Button b = new Button("Afficher");
     
            
            
         
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else 
                {
                    try {
                        
                      locationn l = new locationn(debut.getDate(),fin.getDate() ,tfName.getText());
                   //  locationn loca = ServiceLocation.getInstance().edit1(l, id);
                        if(ServiceLocation.getInstance().edit1(l,id))
                            {
                           /*  location_id= l.getId(); 
                            System.out.println(location_id);*/
                                /*  confirmationlocation sde = new confirmationlocation(l.getId());
                                  sde.getF().show();*/
                           Dialog.show("Success","votre location est envoyée cher client !",new Command("OK"));
                      
               
                            }
                          
                        else  {
                            Dialog.show("ERROR", "Server error", new Command("OK")); }
                       
                        
                          b.addActionListener(new ActionListener() {
              @Override
                public void actionPerformed(ActionEvent evt) {
confirmationlocation sde = new confirmationlocation(id);
                                  sde.getF().show();
                
            //  show sde = new show(v.getId());
                  //  sde.getF().show();
                  
                }
            });        
                     }catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                     
                   
                }
                
                  
              }
            
                        
              
        });
        
         
        
        
        addAll(tfName,debut,fin,btnValider,b);
     //   
    }

   public Form getF() {
        return current;
    }

    
     

}


