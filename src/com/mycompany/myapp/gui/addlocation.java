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
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.locationn;
import com.mycompany.myapp.services.ServiceLocation;
import  com.mycompany.myapp.gui.confirmationlocation ;
import static com.codename1.util.DateUtil.compare;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.velo;
import java.util.ArrayList;
import java.util.Date;




/**
 *
 * @author Hajer
 */
public class addlocation extends Form{ 
        Form  current;
        int id_velo;
        public int location_id;
     locationn l1 = new locationn ();
  boolean test = false;
      


    public addlocation(velo v) {
        this.id_velo = v.getId() ;
       int id=  v.getId();
        current=this;
        setTitle("liste des velos");

         
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
       java.util.Date now = new java.util.Date(); 
        Button btnValider = new Button("Ajouter");
         Button b = new Button("Afficher");
      
    //System.out.println(compare(e,now));System.out.println(compare(e,s));
       
             
              //System.out.println(compare(e,now));
         
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (v.getEtat()==1)
     {
                ServiceLocation ess = new ServiceLocation();
           ArrayList<locationn> listloc= new ArrayList<>();
        listloc = ess.getlocv(id);
       // l1=ess.getEvent3(id);
        
      
        //ArrayList<locationn> listloc = new ArrayList<>();
       //ystem.out.println( l = es.getAllloc(id));
  for (locationn l1 : listloc) {
                Date  s= debut.getDate();
                Date o= l1.getDate_fin();
                
    Date  e1 = fin.getDate() ;
    System.out.println(compare(e1,o));
     
     if (compare(e1,o)==-1) {
        
                Dialog.show("Alert", "vélo est reservé!!", new Command("OK"));
               
              }
  
                else {
                   // Date  e1 = fin.getDate() ;
                      //  Date  s= debut.getDate();
                  if   ((tfName.getText().length()==0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else  if (compare(e1,s)==-1) {
       
             Dialog.show("Alert", "date fin doit etre supérieure", new Command("OK"));}
        
               else if (compare(s,now)==-1) {
        //System.out.println(compare(now,s));
                 Dialog.show("Alert", "vérifier votre date début!!", new Command("OK"));
                }
                 //else if (compare(e1,o)==-1) {
        
                // Dialog.show("Alert", "!!", new Command("OK"));
               // }*/
               //  break;
                else
                {   
                System.out.println(e1);
                  System.out.println(s);
                
                    try {
                        
                      locationn l = new locationn(MyApplication.user.getId(),debut.getDate(),fin.getDate() ,tfName.getText());
                      locationn loca = ServiceLocation.getInstance().addTask(l, id,MyApplication.user.getId() );
                        if(ServiceLocation.getInstance().addTask(l,id,MyApplication.user.getId())!=null)
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
confirmationlocation sde = new confirmationlocation(loca.getId());
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
  }
  }
  
     else {   Date  s= debut.getDate(); 
     Date  e1 = fin.getDate() ;
                      if   ((tfName.getText().length()==0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else  if (compare(e1,s)==-1) {
       
             Dialog.show("Alert", "date fin doit etre supérieure", new Command("OK"));}
        
               //else if (compare(s,now)==-1) {
        //System.out.println(compare(now,s));
              //   Dialog.show("Alert", "vérifier votre date début!!", new Command("OK"));
              //  }
                 //else if (compare(e1,o)==-1) {
        
                // Dialog.show("Alert", "!!", new Command("OK"));
               // }*/
                else
                {   
                System.out.println(e1);
                  System.out.println(s);
                    try {
                        
                      locationn l = new locationn(debut.getDate(),fin.getDate() ,tfName.getText());
                      locationn loca = ServiceLocation.getInstance().addTask(l, id,MyApplication.user.getId());
                        if(ServiceLocation.getInstance().addTask(l,id,MyApplication.user.getId())!=null)
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
confirmationlocation sde = new confirmationlocation(loca.getId());
                                  sde.getF().show();
                
            //  show sde = new show(v.getId());
                  //  sde.getF().show();
                  
                }
            });        
                     }catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                      
                   
                }
                
        //    }  
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




