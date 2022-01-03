/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.services.coursService;
import com.mycompany.myapp.services.paiementService;

import com.mycompany.myapp.utils.share;
//import com.mycompany.Service.UserEventService;

/**
 *
 * @author El Kamel
 */
public class détailscours {

    Form f;
    int cour_id;
   cours e = new cours(cour_id);
    int user_id;

    //int SelectedID;
    public détailscours(cours cours) {
        f = new Form();


        this.e = cours;
        
     
             
        f.setTitle("Event details");

        f.setLayout(BoxLayout.y());    
         
        Toolbar.setGlobalToolbar(true);
        f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new HomeForm().show();

         }
     });
        
        
          f.getToolbar().addCommandToSideMenu("Events", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          coursForm events = new coursForm();
           events.getF().show();
            }
        });
          
            
        f = new Form();
        f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            coursForm sb = new coursForm();
            sb.getF().show();
        });

       cours ev = new cours(e.getId());
        coursService es = new coursService();

        ev = es.getcours(cours, e.getId());

        Container c1 = new Container(BoxLayout.y());

        Label label = new Label(e.getLieu_cours());
        c1.add(label);

        Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
              imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image(), "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
        c1.add(img1);

        Container c2 = new Container(BoxLayout.x());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        final FontImage time = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        c2.add(time);
        c2.add(new Label("Date : "));
        c2.add(new Label(formatter.format(e.getDate_cours())));
        ///
   Container c8 = new Container(BoxLayout.x());
        final FontImage li = FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, "Label", 6);
        c8.add(li);
        c8.add(new Label("Lieu : "));
       String lieu = e.getLieu_cours();
       
        c8.add(new Label(lieu));
        c8.add(new Label("DT"));

      

        //PRIX
        Container c6 = new Container(BoxLayout.x());
        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c6.add(prx);
        c6.add(new Label("Prix : "));
       int prix = e.getPrixcours();
        String s1 = Integer.toString(prix);
        c6.add(new Label(s1));
        c6.add(new Label("DT"));

       

       

   FloatingActionButton sig = FloatingActionButton.createFAB(FontImage.MATERIAL_REPORT);

            sig.addActionListener(new ActionListener() {
                @Override
                 public void actionPerformed(ActionEvent evt) {

                 //e.setSignale(e.getSignale()+1);
    coursService.getInstance().signaler(e.getId());
                           Dialog.show("Cours signalé"," Demande enregistrée et en cours de traitement",new Command("OK"));
                           if (e.getSignale()>=5) {
                                  coursService.getInstance().removecours(e.getId());
                                  Dialog.show("Cours signalé"," Demande traitée et Cours suprimé",new Command("OK"));
                                  
                           }
                
               

                }
            });
              FloatingActionButton btnPartager = FloatingActionButton.createFAB(FontImage.MATERIAL_SHARE);
                  
        btnPartager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                
                share s = new share();
                s.share(e.getNom_image(),e);

            }
        });
        FloatingActionButton eval = FloatingActionButton.createFAB(FontImage.MATERIAL_RATE_REVIEW);
      eval.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt1) {
            
      
              
                EvaluationForm y = new EvaluationForm(e);

              
                    y.getF().show();}
            });
      

       int nb = e.getNb();

            Container c5 = new Container(BoxLayout.x());
           
              FloatingActionButton btnReserver = FloatingActionButton.createFAB(FontImage.MATERIAL_HOW_TO_REG);
            btnReserver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt1) {
                      if (e.getNb()<10) {    
             Dialog.show("felicitations", "cours disponible !", new Command("OK"));
         // coursForm z = new coursForm();
              
                 Addpaiement z = new Addpaiement(e.getId());

              
                    z.getF().show();}
                      
else if (e.getNb()>=10){
             Dialog.show("alert ", "Cours n'est plus disponible  !", new Command("OK"));}

            
     }});
       
        
           
c5.add(sig);
           
        c5.add(btnReserver);
       c5.add(btnPartager);
       c5.add(eval);

        c1.add(c2);
        
        c1.add(c6);
        c1.add(c8);
    
        c1.add(c5);

        f.add(c1);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  
}