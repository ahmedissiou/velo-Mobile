/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.*;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;


/**
 *
 * @author asus
 */
public class CommandeForm extends Form {
    private ImageViewer img;
    private Commande cmd;
     Form current;
    // Produit pp;
       public CommandeForm() throws Exception{
        
           super();
           current=this;
        
        //setTitle("Liste des commandes");
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
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
            
        
      

        Container CntRR = new Container();
        Container Cntr1 = new Container();
        Container CntR = new Container();
        Container cnt2 = new Container(BoxLayout.y());
        Container cnt3 = new Container(BoxLayout.x());
        this.setLayout(BoxLayout.y());
        this.setTitle("Liste des commandes");
        //SpanLabel Sp = new SpanLabel(CoursService.getInstance().getAllFavoris().toString());
        ArrayList<Commande> list = new ArrayList();
        list = CommandeService.getInstance().getCommande(Session.getCurrentSession());
        for (int i = 0;i < CommandeService.getInstance().getCommande(Session.getCurrentSession()).size();i++) {

            //img = new ImageViewer(theme.getImage("img12.jpg"));
            Container cnt1 = new Container(BoxLayout.x());

            Container cnt = new Container(BoxLayout.y());
            Label num = new Label("Réf : CMD"+Integer.toString(list.get(i).getId()));
            Label prix = new Label("Prix Total: " +Double.toString(list.get(i).getPrixtotal()));
            Label lb3 = new Label("L'état de commande: " +list.get(i).getEtat());
            
   

         Container c1 = new Container(BoxLayout.x()); 
        final FontImage sup = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Label", 6);
            Button bt1 =new Button("Supprimer");
            Commande F=list.get(i);

            bt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   ArrayList<Commande> list1 = new ArrayList();

                    try {
                        //if(
                        CommandeService.getInstance().Supprimer(F.getId());
                       // Dialog.show("Success", "Commande supprimée ", "OK", null);
          
                        list1 = CommandeService.getInstance().getCommande(Session.getCurrentSession());
                          CommandeForm r = new CommandeForm();
                           r.getF().show();
                    } catch (Exception ex) {
                     
                    }

                }
            });
           
            c1.add(bt1);
            c1.add(sup);
            
            cnt.add(num);
            cnt.add(prix);
            cnt.add(lb3);
             if(list.get(i).getEtat().equals("en cours"))
            {
            cnt.add(c1);
            }else if(list.get(i).getEtat().equals("Payée"))
            {
               Container val = new Container(BoxLayout.x()); 
              final FontImage val1 = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, "Label", 6);
              final FontImage val2 = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, "Label", 6);
              final FontImage val3 = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, "Label", 6);
              val.add(val1);
              val.add(val2);
              val.add(val3);
              cnt.add(val);
            
            }else{
                 Container val = new Container(BoxLayout.x());
            Button bp =new Button("Payer");
            bp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                        Paiementwissal r = new Paiementwissal(F);
                           r.getF().show();

                }
            });
               //final FontImage val1 = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, "Label", 6);
              // final FontImage val2 = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, "Label", 6);
              // final FontImage val3 = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, "Label", 6);
              // val.add(val1);
               //val.add(val2);
              // val.add(val3);
              // cnt.add(val);
               cnt.add(bp);
             }
           
            ///cnt.add(Sp);

           // cnt.add(CntRR);
            cnt1.add(cnt);
            this.add(cnt1);;
            this.show();


        }
    }
       
    public Form getF() {
        return current;
    }
    

    
}
