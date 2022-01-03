/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Addresse;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.services.AddresseService;
import com.mycompany.myapp.services.CommandeService;
import com.mycompany.myapp.utils.Statics;
import com.teknikindustries.bulksms.SMS;





/**
 *
 * @author asus
 */
public class ValiderForm extends Form {
     Form current;
     Produit pp;
     
     public ValiderForm(){
           current=this;
       setTitle("Confirmer Commande");

         
        //setLayout(BoxLayout.y());
        
        current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                  new HomeForm().show();

            }
        });
       current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new HomeForm().show();

         }
     });
         current.getToolbar().addMaterialCommandToSideMenu("      Profil", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          //coursForm events = new coursForm();
         //  events.getF().show();
            }
        });
      
          current.getToolbar().addMaterialCommandToSideMenu("       Cours", FontImage.MATERIAL_SCHOOL, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
        //  coursForm events = new coursForm();
          // events.getF().show();
            }
        });
            current.getToolbar().addMaterialCommandToSideMenu("       Evénements", FontImage.MATERIAL_EVENT, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
         // coursForm events = new coursForm();
          // events.getF().show();
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
      current.getToolbar().addMaterialCommandToSideMenu("       Louer", FontImage.MATERIAL_STORE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
         // coursForm events = new coursForm();
           //events.getF().show();
            }
        });
      current.getToolbar().addMaterialCommandToSideMenu("       Réparer", FontImage.MATERIAL_BUILD, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
         // coursForm events = new coursForm();
           //events.getF().show();
            }
        });
    current.getToolbar().addMaterialCommandToSideMenu("       Réclamer", FontImage.MATERIAL_FEEDBACK, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
         // coursForm events = new coursForm();
          // events.getF().show();
            }
        });
       current.getToolbar().addMaterialCommandToSideMenu("       Site web", FontImage.MATERIAL_PUBLIC, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
        FileSystemStorage fs = FileSystemStorage.getInstance();
               String fileName = Statics.BASE_URL;
             //   if(!fs.exists(fileName)) {
                    Util.downloadUrlToFile(fileName,fileName, true);
               // }
                Display.getInstance().execute(fileName);
            }
        });
            
        
      
         Container c1 = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.y());
         //this.setLayout(BoxLayout.y());
        //this.setTitle("Valides Commande");
        Container cc1 = new Container(BoxLayout.x());
        Label lb1=new Label("numTel");
        TextField tx1=new TextField("");
       cc1.add(lb1);
       cc1.add(tx1);
    
        Container cc2 = new Container(BoxLayout.x());
        Label lb2=new Label("mail");
        TextField tx2=new TextField("");
        cc2.add(lb2);
       cc2.add(tx2);

        Container cc3 = new Container(BoxLayout.x());
        Label lb4=new Label("pays");
        TextField tx4=new TextField("");
        cc3.add(lb4);
       cc3.add(tx4);
        
         Container cc4 = new Container(BoxLayout.x());
       Label lb3=new Label("ville");
        TextField tx3=new TextField("");
       cc4.add(lb3);
       cc4.add(tx3);
        
         Container cc5 = new Container(BoxLayout.x());
        Label lb5=new Label("pinCode");
        TextField tx5=new TextField("");
        cc5.add(lb5);
        cc5.add(tx5);
        
        Button bt=new Button("Valider");
        bt.addActionListener((evt) -> {
             if (tx1.getText().length() == 0 || tx2.getText().length() == 0 || tx3.getText().length() == 0 
                     || tx4.getText().length() == 0 || tx5.getText().length() == 0 ) {
            Dialog.show("Erreur", "Merci de remplir tous les champs", "OK", null);}
             else if (tx5.getText().length() != 4) {
            Dialog.show("Erreur", "Code pin doit contenir 4 chiffres", "OK", null);}
             else if (tx1.getText().length() != 8) {
            Dialog.show("Erreur", "Numéro se téléphone doit contenir 8 chiffres", "OK", null);}
            else if ((!tx2.getText().contains("@")) || (!tx2.getText().contains("."))) {
            Dialog.show("Erreur", "Email non valide", "OK", null);}
             
             else{
            
   
                   Addresse r = new Addresse(Integer.parseInt(tx1.getText()),tx2.getText(),tx4.getText(),tx3.getText(),Integer.parseInt(tx5.getText()));
                   Addresse rr = AddresseService.getInstance().addAddresse(r);
                   Dialog.show("Success", "Commande validée", "OK", null);

               try {
                 // Commande c = new Commande();
                   CommandeService.getInstance().addcmd(rr.getId(),Session.getCurrentSession());
               } catch (Exception ex) {
              
               }
       
                 SMS sms=new SMS();
                 String nt= "+216"+tx1.getText();
                 sms.SendSMS("wissalpi13","wissalA1", "Votre commande est validée", nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                  
 
                ProduitForm g = new ProduitForm();
                g.getF().show();  
             }
          
                    });
        
       c2.add(cc1);
       c2.add(cc2);
       c2.add(cc3);
       c2.add(cc4);
       c2.add(cc5);
       c2.add(bt);
       c1.add(c2);
       this.add(c1);;
       this.show();
     }
     
    public Form getF() {
        return current;
    }
    
}
