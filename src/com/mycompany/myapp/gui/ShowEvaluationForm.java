/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
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
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.services.EvaluationService;

/**
 *
 * @author fedy
 */
public class ShowEvaluationForm extends Form {
    

    
Form current;
 

    int id;
    cours e = new cours(id);
   // cours e = new cours(event_id);


 public ShowEvaluationForm(cours cours, Evaluations evaluation) {
    // this.reservation_id=id;
   //  this.event_id = id;
   
        this.e=cours;

      current=this;
        setTitle("Détails de l'évaluation");
      
        
       
       
        current.setLayout(BoxLayout.y());
          //     current.setUIID("bg1");

        Toolbar tb = current.getToolbar();
           tb.addMaterialCommandToOverflowMenu("Logout", FontImage.MATERIAL_INPUT, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });
         
          tb.addMaterialCommandToOverflowMenu("cours", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new coursForm().getF().show();

            }
        });
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });

        

           
                setLayout(BoxLayout.y());
        
        Container c1 = new Container(BoxLayout.xCenter());
       // final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_TITLE, "Label",3);
        //c1.add(adr);
        
       Evaluations ev = new Evaluations(e.getId());
        EvaluationService es = new EvaluationService();

        ev = es.getEv2(ev, id);
       
       
        Label l8 = new Label(cours.getLieu_cours());
       // l8.setUIID("TitreEvent");
        c1.add(l8);
        
        current.add(c1);
        
        Container c0 = new Container(BoxLayout.y());
         Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + cours.getNom_image(), "http://localhost/pifinal/pidevfinal/web/images/" + cours.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
       
        c0.add(img1);
        current.add(c0);

        
        EvaluationService sr = new EvaluationService();
        Evaluations rs = new Evaluations(evaluation.getId());
       // ArrayList<Reservations> listRes = new ArrayList<>();
       //rs = sr.getRes2(reservation,reservation.getId());
     //  rs = sr.getEv2(evaluation, evaluation.getId());

        Container c2 = new Container(BoxLayout.x());
        final FontImage adr1 = FontImage.createMaterial(FontImage.MATERIAL_CONFIRMATION_NUMBER, "Label", 6);
        c2.add(adr1);
       c2.add(new Label("Note :"));
       c2.add(new Label(Integer.toString(rs.getNote())));
        current.add(c2);
       
       
       
        Container c3 = new Container(BoxLayout.x());
        final FontImage adr2 = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c3.add(adr2);
        c3.add(new Label("Commentaire : "));
        c3.add(new Label(rs.getCommentaire()));        
        current.add(c3);
        

        /* Container c0 = new Container(BoxLayout.y());
         Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/cours/" + events.getNom_image(), "http://localhost/projet_3a/symfony/web/cours/" + events.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
       
        c0.add(img1);
        current.add(c0);
  }
 
   public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
    
     /*public ShowReservationsForm(Form current,Reservations reservation) {
        setTitle("Show Reservation");
        this.r = reservation;
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceReservation.getInstance().getRes2(reservation, reservation.getId()).toString());
        add(sp);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }*/
     
 }
}
