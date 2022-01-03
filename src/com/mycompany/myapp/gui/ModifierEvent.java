/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.OnOffSwitch;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
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
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.EventService;
import java.io.IOException;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Manel 
 */
public class ModifierEvent  extends Form {
        Form ModifierEvent;
        private FileUploader file ; 
        String FileNameInServer ; 
        private String imgPath ; 
        private static int id ;
        
        
        
 public ModifierEvent(int id ) {
     EventService es = new EventService();
     Event e =   es.afficherevent(id);
    ModifierEvent= new Form("Modifier un evenement ", BoxLayout.y());
        TextField tnom = new TextField(e.getNomevet() , "saisir nom");
        TextField tlieu = new TextField(e.getLieuevt(), "saisir lieu");
        TextField tdescription  = new TextField(e.getDescription(), "saisir la description ");
        TextField tprix  = new TextField(Integer.toString(e.getPrixe()), "saisir le prix ");
        TextField tnbparticipents= new TextField(Integer.toString(e.getNbparticipent()), "saisir le nombre de participants ");
       
        Container cnt = new Container(BoxLayout.x());
        Label lbDeb=new Label("Date début "); 
        Picker datedebe = new Picker();
        datedebe.setDate(e.getDate_debe());
        cnt.addAll(lbDeb,datedebe) ; 
        
        Container cnt2 = new Container(BoxLayout.x());
        Label lbFin=new Label("Date Fin  "); 
        Picker datefine = new Picker(); 
        datefine.setDate(e.getDate_fine());
        cnt2.addAll(lbFin,datefine) ; 
        
        Container cnt3 = new Container(BoxLayout.x());
        Label lbimg=new Label("image "); 
        Button picture  = new Button ("Parcourir ") ;
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        cnt3.addAll(lbimg,picture) ; 
        FileNameInServer = e.getNom_image();
     picture.addPointerReleasedListener(new ActionListener(){ 
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
         ModifierEvent.addAll(tnom,tlieu,tdescription,tprix,tnbparticipents,cnt,cnt2,cnt3,btnenregistrer) ; 
    
         btnenregistrer.addActionListener(ev-> {

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
              else {
              EventService evsevice = new EventService();
              Event e1 = new Event(id,tnom.getText(), datedebe.getDate(), datefine.getDate(),tlieu.getText() , tdescription.getText(), Integer.parseInt(tnbparticipents.getText()), Integer.parseInt(tprix.getText()), FileNameInServer, e.getCreator_id(), e.getNbsignal(), e.getIsActive());
              evsevice.modifier(e1);
              Dialog.show("Succés", "Evenement modifié avec succé ", "ok", null);

              }
             
         
             
         });
        
         
            ModifierEvent .getToolbar().addCommandToOverflowMenu("Back ", null, evd-> { 
                 AfficherAllEvents aff = new AfficherAllEvents();
                 aff.getF().showBack(); });
         
}

    public Form getF() {
        return ModifierEvent ;
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