/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.util.DateUtil.compare;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.locationn;
import com.mycompany.myapp.services.ServiceLocation;
import static java.lang.System.currentTimeMillis;
import java.util.ArrayList;


import java.util.Date;



/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

     //locationn l  = new locationn();  
Form current;
    public HomeForm()  {
        current=this;
        setTitle("Velo.tn");
  Container c = new Container(BoxLayout.x());
         
        setLayout(BoxLayout.y());
     
       current.setUIID("Ahmed");
     
        
        add(new Label("Bienvenu a VELO.TN"));
        current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                         MyApplication.user=null;
                SignInForm m=new SignInForm();
                m.show();

         }

           
     });
         current.getToolbar().addMaterialCommandToSideMenu("         Profil", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                         Profile profile = new Profile();
                profile.getF().show();    
          
            }
        });
      
          current.getToolbar().addMaterialCommandToSideMenu("       Cours", FontImage.MATERIAL_SCHOOL, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          coursForm events = new coursForm();
           events.getF().show();
            }
        });
            current.getToolbar().addMaterialCommandToSideMenu("         Evénements", FontImage.MATERIAL_EVENT, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                   AfficherAllEvents events = new AfficherAllEvents();
           events.getF().show();
         
            }
        });
          current.getToolbar().addMaterialCommandToSideMenu("       Liste produits", FontImage.MATERIAL_ADD_SHOPPING_CART, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                    ProduitForm events = new ProduitForm();
                   events.getF().show();
            }
        });
     current.getToolbar().addMaterialCommandToSideMenu("       Mon Panier", FontImage.MATERIAL_SHOPPING_CART, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                       try {
                     PanierForm about = new PanierForm();
                     about.getF().show();
                    
                 } catch (Exception ex) {
                   
                 }
            }
        });
    current.getToolbar().addMaterialCommandToSideMenu("       Mes Commande", FontImage.MATERIAL_STYLE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                      try {
                  CommandeForm about = new CommandeForm();
                  about.getF().show();
                 } catch (Exception ex) {
                    
                 }
            }
        });
                          current.getToolbar().addMaterialCommandToSideMenu("      Louer", FontImage.MATERIAL_STORE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                listvelo e = new  listvelo();
          // new listveloForm().getF().show();
           e.getF().show();
            
       
          
            }
        });
                       current.getToolbar().addMaterialCommandToSideMenu("      Réparer", FontImage.MATERIAL_BUILD, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                  repForm r = new repForm();
           r.getF().show();
          
            }
        });
                                     current.getToolbar().addMaterialCommandToSideMenu("      Réclamer", FontImage.MATERIAL_FEEDBACK, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                AddreclamationForm r = new AddreclamationForm();
           r.getF().show();
          
            }
        });
       current.getToolbar().addMaterialCommandToSideMenu("      calendar", FontImage.MATERIAL_DATE_RANGE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
         CalendarForm sa = new CalendarForm();
           sa.getF().show();
            }
        });
        current.getToolbar().addMaterialCommandToSideMenu("       Nos Chiffres d'affaires", FontImage.MATERIAL_TRENDING_UP, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
         StatsForm dd = new StatsForm();
           dd.getF().show();
            }
        });
        
      
        /*  current.getToolbar().addCommandToSideMenu("Liste des velos", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
               listvelo e = new  listvelo();
          // new listveloForm().getF().show();
           e.getF().show();
            }
        });
           
           current.getToolbar().addCommandToSideMenu("Reclamation", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          AddreclamationForm r = new AddreclamationForm();
           r.getF().show();
            }
        });*/
       int id=MyApplication.user.getId();
           ServiceLocation es = new ServiceLocation();
           ArrayList<locationn> listloc= new ArrayList<>();
        listloc = es.getAllloc(id);
      
        //ArrayList<locationn> listloc = new ArrayList<>();
       //ystem.out.println( l = es.getAllloc(id));
   for (locationn l : listloc) {
        // Date d1 = sdformat.parse("2019-04-15");
        Date now = new Date();
        //   DateFormat df = new SimpleDateFormat("dd/MM/yy");
        
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       
  SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy");
      String t = formatter.format(now);
       String o =formatter.format(l.getDate_debut());
       System.out.println(t);
        System.out.println(o);
   if(t.equals(o)) {
  System.out.println("Les deux String sont égaux");}
       // Date endcomp = Date.valueOf(tfendcomp.getValue());
/* System.out.println(now);
            Date da = l.getDate_debut();
            //System.out.println( formatter.format(l.getDate_debut()));
   System.out.println(l.getDate_debut());
 // if (da.compareTo(startcomp)<0) 
  // SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
     // Date d1 = sdformat.parse("2019-04-15");
     System.out.println( compare(da , now));
     if( compare(da,now)==-1)*/
 if(t.equals(o)) 
     {
      LocalNotification n = new LocalNotification();
      n.setId("demo-notification");
      n.setAlertBody("votre loction est aujourd'hui , on vous attend cher client");
      n.setAlertTitle("notification");
      n.setAlertSound("/notification_sound_beep-01a.mp3");
  
 String path =  "file://C:/Users/Hajer/Documents/NetBeansProjects/mobilepi/src/mobille.jpg" ;
    n.setAlertImage(path);

 

//System.out.println(currentTimeMillis());



Display.getInstance().scheduleLocalNotification(
        n,
        System.currentTimeMillis(), // fire date/time
        LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
);      
  //
     }
     else {
         LocalNotification n1 = new LocalNotification();
      n1.setId("demo-notification");
      n1.setAlertBody("Vous allez adorer ! on a des promotions , ouvrez vite "
              + "pour en profiter tout de suite !");
      n1.setAlertTitle("notification");
      n1.setAlertSound("/notification_sound_beep-01a.mp3");
        n1.setAlertImage("mobille.jpg");
        
Display.getInstance().scheduleLocalNotification(
        n1,
        System.currentTimeMillis(), // fire date/time
        LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
); 
        
     }
       
       
   }
        

   }
        
    

    

    
    public Form getF() {
        return current ;
    }

    public void setF(Form f) {
        this.current = current;
    }


}
