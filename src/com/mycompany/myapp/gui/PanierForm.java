/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;




import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.mycompany.myapp.entities.*;
import com.mycompany.myapp.services.*;
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
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class PanierForm extends Form {
    
     Form current;
    
       public PanierForm() throws Exception {
           super();
           current=this;
        
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
            
        
      

       // Container CntRR = new Container();
        //Container Cntr1 = new Container();
        //Container CntR = new Container();
        //Container cnt2 = new Container(BoxLayout.y());
        //Container cnt3 = new Container(BoxLayout.x());
        this.setLayout(BoxLayout.y());
        this.setTitle("Mon Panier");
        //SpanLabel Sp = new SpanLabel(CoursService.getInstance().getAllFavoris().toString());
         ArrayList<Panier> list = new ArrayList();
      
        list = PanierService.getInstance().getpppuser(Session.getCurrentSession());
         for (int i = 0 ;i < PanierService.getInstance().getpppuser(Session.getCurrentSession()).size();i++) {
           Panier p = list.get(i);
            //img = new ImageViewer(theme.getImage("img12.jpg"));
            Container cnt1 = new Container(BoxLayout.x());
            
            Container cnt = new Container(BoxLayout.y());
            
            Label num = new Label("Réf : P"+Integer.toString(list.get(i).getId()));
            Label prd = new Label("Produit: Prd" +Integer.toString(p.getProduit_id()));
            Label qte = new Label("Quantité: " +list.get(i).getQuantite());
           
            
             cnt.add(num);
            cnt.add(prd);
            cnt.add(qte);
            

         Container c1 = new Container(BoxLayout.x()); 
            Button b1 =  new Button("+");
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ArrayList<Panier> list1 = new ArrayList();
                    try {
                        //addpanier(p);
                         PanierService.getInstance().modppp(p.getProduit_id(), p.getId(), 1);
                        list1 = PanierService.getInstance().getpppuser(Session.getCurrentSession());
                           PanierForm r = new PanierForm();
                           r.getF().show();
                } catch (Exception ex) {
                     
                    }

                }
            });
            Button b2 =  new Button("-");
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ArrayList<Panier> list1 = new ArrayList();
                    try {
                        if (p.getQuantite()==0){
                        PanierService.getInstance().Supprimer(p);
                        }
                        else{
                            PanierService.getInstance().modppp(p.getProduit_id(), p.getId(), 0);
                        }
                       // removepanier(p);
                        list1 = PanierService.getInstance().getpppuser(Session.getCurrentSession());
                         PanierForm r = new PanierForm();
                           r.getF().show();
                         } catch (Exception ex) {
                    }
                }
            });
           Button bt1 =new Button("Supprimer");
            bt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   // ArrayList<Panier> list1 = new ArrayList();
                    try {
                        PanierService.getInstance().Supprimer(p);
                        Dialog.show("Success", "Panier supprimé ", "OK", null);
                        //list1 = PanierService.getInstance().getpppuser(Session.getCurrentSession());
                        PanierForm r = new PanierForm();
                        r.getF().show();
                         } catch (Exception ex) {
                     
                    }
                        }
            });
            
            c1.add(b1);
            c1.add(b2);
            c1.add(bt1);
           
            cnt.add(c1);
            
            ///cnt.add(Sp);

           // cnt.add(CntRR);
            cnt1.add(cnt);
            
            this.add(cnt1);
           
     } 
            Button bb = new Button("Confirmer");
            bb.addActionListener((evt) -> {
      
               // Addresse a= new Addresse();
               // AddresseService.getInstance().addAddresse(a);
            ValiderForm r = new ValiderForm();
            r.getF().show();
            
         });
            this.add(bb);
             this.show();
        // current.show();
                 
   
         
}    

    public void addpanier(Panier p){
     p.setQuantite(p.getQuantite()+1);
    }
    public void removepanier(Panier p){
     p.setQuantite(p.getQuantite()-1);
    }
    
     

    public Form getF() {
        return current;
    }
    
}
