/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.mycompany.myapp.services.*;
import com.mycompany.myapp.entities.Produit;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;

import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;


import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;






/**
 *
 * @author asus
 */
public class ProduitForm extends Form {
      Form current;
      Produit pp;
       

    
    public ProduitForm(){
        
     current= new Form(); 
     
    current=this;
       setTitle("Liste des produits");

         
       
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
        Container c1 = new Container(BoxLayout.y());

        
       Image imgUrl;
       ProduitService es = new ProduitService();
        ArrayList<Produit> listprd = new ArrayList<>();
        listprd = es.getproduit();

        Label label = new Label("");

       
        c1.add(label);

        for (Produit e : listprd) {
              Container ccc= new Container(BoxLayout.x());
              final FontImage num1 = FontImage.createMaterial(FontImage.MATERIAL_STYLE, "Label", 4);
              Label num = new Label("Réf : Prd"+Integer.toString(e.getId()));
             ccc.add(num1); 
             ccc.add(num);
             
             
             Container cc = new Container(BoxLayout.x());
              final FontImage nom = FontImage.createMaterial(FontImage.MATERIAL_DIRECTIONS_BIKE, "Label", 4);
              Label nom1 = new Label("Nom de produit: " +e.getNom());
             cc.add(nom); 
             cc.add(nom1);
             
             Container c2 = new Container(BoxLayout.x());
              final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 4);
              Label prix = new Label("Prix: " +Double.toString(e.getPrix()));
             c2.add(prx); 
             c2.add(prix);
            
            
             c1.add(ccc);
             c1.add(cc);
             c1.add(c2);

            Image placeholder = Image.createImage(1280, 720); 
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

           
imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/"  + e.getImage(), "http://localhost/pifinal/pidevfinal/web/images/"  + e.getImage());
           
Container c3 = new Container(BoxLayout.x()); 
final FontImage shop = FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, "Label", 6);
Button b = new Button("ajouter au panier" );
     

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (e.getQuantite() ==0 ) {
                        Dialog.show(":(", "Produit non disponible" , "OK", null); 
                    }else{
                            
                       
                      //  ArrayList<Produit> list1 = new ArrayList();
                       
                        try { 
                            
                          // Panier p = new Panier(e.getId(),1,Session.getCurrentSession());
                            
                            PanierService.getInstance().addppp(e.getId(),Session.getCurrentSession());
                            Dialog.show("Success", "Produit ajouté au panier", "OK", null);
                            //list1 = ProduitService.getInstance().getproduit();
                            PanierForm r = new PanierForm();
                             r.getF().show();
                        } catch (Exception ex) {
                           
                        } }

                    }
                });
            
           c1.add(imgUrl);
           
           c3.add(b);
           c3.add(shop); 
           
           c1.add(c3);
           

           
        }
         
        current.add(c1);

    
    }
     
 

    public Form getF() {
        return current;
    }
    


      
            
      

 }
