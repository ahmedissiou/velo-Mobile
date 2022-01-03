/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
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
import com.codename1.util.DateUtil;
import static com.codename1.util.DateUtil.compare;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.EventService;
import java.io.IOException;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Manel 
 */
public class AjouterEvent  extends Form {
        Form ajouterEvent;
        private FileUploader file ; 
        String FileNameInServer ; 
        private String imgPath ; 
        
        
 public AjouterEvent() {
    ajouterEvent = new Form("Ajouter un evenement ", BoxLayout.y());
        TextField tnom = new TextField("" , "saisir nom");
        TextField tlieu = new TextField("", "saisir lieu");
        TextField tdescription  = new TextField("", "saisir la description ");
        TextField tprix  = new TextField("", "saisir le prix ");
        TextField tnbparticipents= new TextField("", "saisir le nombre de participants ");
       
        Container cnt = new Container(BoxLayout.x());
        Label lbDeb=new Label("Date début "); 
        Picker datedebe = new Picker();
        cnt.addAll(lbDeb,datedebe) ; 
        
        Container cnt2 = new Container(BoxLayout.x());
        Label lbFin=new Label("Date Fin  "); 
        Picker datefine = new Picker(); 
        cnt2.addAll(lbFin,datefine) ; 
        
        Container cnt3 = new Container(BoxLayout.x());
        Label lbimg=new Label("image "); 
        Button picture  = new Button ("Parcourir ") ;
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        cnt3.addAll(lbimg,picture) ; 
        
     picture.addActionListener(new ActionListener(){ 
        @Override
      public void actionPerformed (ActionEvent evt ) {
     try {
         imgPath =  Capture.capturePhoto(); 
         System.out.println(imgPath);
         String link  = imgPath.toString();
         int pod = link.indexOf("/",2 ) ; 
         String news = link.substring(pod +2 , link.length()) ;
         System.out.println(""+ news );
         FileUploader fu =  new FileUploader("http://localhost/pifinal/pidevfinal/web");
         FileNameInServer = fu.upload(news) ; 
                  System.out.println(FileNameInServer);

      //   path.setText(FileNameInServer); 
         
     }
     catch (IOException ex ){
         ex.printStackTrace() ; 
     }
     catch (Exception ex ) {
 }
 }
 });
 

        Button btnenregistrer = new Button ("Enregistrer ") ; 
         ajouterEvent.addAll(tnom,tlieu,tdescription,tprix,tnbparticipents,cnt,cnt2,cnt3,btnenregistrer) ; 
    
         btnenregistrer.addActionListener(ev-> {
                  
          
         
            
java.util.Date now = new java.util.Date();
             
             if (  datedebe.getText().isEmpty() ) {
                        Dialog.show("Erreur", " verifier la date de début de l'evenement" , "OK", null); 
             }
               else if (datefine.getText().isEmpty()) {
            Dialog.show("Erreur", "verifier la date de fin de l'evenement  ", "OK", null);}
             
               else if (tdescription.getText().isEmpty()) {
            Dialog.show("Erreur", "verifier la description de l'evenement ", "OK", null);}
               else if (tlieu.getText().isEmpty()) {
            Dialog.show("Erreur", "verifier le lieu de l'evenement ", "OK", null);}
                else if (tnom.getText().isEmpty()) {
            Dialog.show("Erreur", "verifier le nom de l'evenement ", "OK", null);}
              else if (isNotInteger(tprix.getText())) {
            Dialog.show("Erreur", "le prix  ne peut contenir que des chiffres", "OK", null);}
             
              else if (Integer.parseInt(tprix.getText())< 0 ) {
            Dialog.show("Erreur", "le prix  doit etre positif ", "OK", null);}
             
              else if (isNotInteger(tnbparticipents.getText())) {
            Dialog.show("Erreur", "le nombres des participants ne peut contenir que des chiffres", "OK", null);}
             
              else if (Integer.parseInt(tnbparticipents.getText())< 0 ) {
            Dialog.show("Erreur", "le nombre des participants  doit etre positif ", "OK", null);}
              
                 else if (new DateUtil().compare(datedebe.getDate(),datefine.getDate())== 1) {
            Dialog.show("Erreur", "verifier date  ", "OK", null);}
                 
                 else if (compare(datedebe.getDate(),now)==-1) {
        
                 Dialog.show("Alert", "date eronnée","OK", null ); }
              
              else {
              EventService evsevice = new EventService();
              Event e = new Event(tnom.getText(), datedebe.getDate(), datefine.getDate(),tlieu.getText() , tdescription.getText(), Integer.parseInt(tnbparticipents.getText()), Integer.parseInt(tprix.getText()), FileNameInServer,  MyApplication.user.getId(), 0, true);
              evsevice.ajoutEvent(e);
              Dialog.show("Succés", "Evenement ajouté  avec succé ", "ok", null);
              AfficherAllEvents aff = new AfficherAllEvents();
                aff.getF().showBack();
              }
         });
        
         
         
            ajouterEvent.getToolbar().addCommandToOverflowMenu("Back ", null, evd-> {
                AfficherAllEvents aff = new AfficherAllEvents();
                   aff.getF().showBack();
         }); 
         
}

    public Form getF() {
        return ajouterEvent;
    }

          public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
}