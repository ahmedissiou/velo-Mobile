package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Util;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.PaiementStripe;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.PaiementStripe;
import com.mycompany.myapp.services.CommandeService;
import com.mycompany.myapp.utils.Statics;


import com.stripe.model.Charge;
import com.stripe.model.Token;



/**
 *
 * @author berrahal
 */
public class Paiementwissal extends Form{
    int cour_id;
    int id;
    Form current;
   Commande e = new Commande(id);
  
Form f;
 
    Container c3 = new Container(BoxLayout.x());

    Button btnaff = new Button("Payer");
 
   
     Button anuler= new Button("Annuler");
     
   
    public Paiementwissal(Commande cmd) {
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
     
    
    
       
      
          anuler.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                 HomeForm h = new HomeForm();
                    h.getF().show();

            }  });
         
       
           
            
            
        

        
               final FontImage back = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,"Label", 6);
     
       final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 11);
          final FontImage bike = FontImage.createMaterial(FontImage.MATERIAL_DIRECTIONS_BIKE, "Label", 11);
        f = new Form("Paiement  "+cmd.getPrixtotal()+"DT" );
                  f.add(prx);
                      f.add(bike);

      TextField num = new TextField("", "Numéro de carte") ;
     
        TextField mois = new TextField("", "mois d'expirtion") ;
        TextField annee = new TextField("", "annee d'expiration") ;
        TextField cvv = new TextField("", "CVC") ;
                TextField email = new TextField("", "Adresse email") ;
               
                btnaff.addActionListener((evt) -> {
                    if (num.getText()=="" || mois.getText()=="" || annee.getText()=="" || email.getText()=="" ) {
                        Dialog.show("Erreur", "Merci de vérifier vos informations" , "OK", null); 
                    }
                    else if ((!email.getText().contains("@")) || (!email.getText().contains("."))) {
            Dialog.show("Erreur", "Email non valide", "OK", null);}
                    else if (isNotInteger(cvv.getText())) {
            Dialog.show("Erreur", "CVC ne peut contenir que des chiffres", "OK", null);}
                    
                    else if (cvv.getText().length() != 3) {
            Dialog.show("Erreur", "CVC doit contenir 3 chiffres", "OK", null);}
              else if (num.getText().length() != 16) {
            Dialog.show("Erreur", "Code erroné ", "OK", null);
           
        } 
                    
                    
                    else{
                        
                
 
                    int mois0 = Integer.parseInt(mois.getText());
        int annee0 = Integer.parseInt(annee.getText());
                
                                    Token token = PaiementStripe.getToken("pk_test_AuAMdXwE57NnBcd4Xld65Ez4", num.getText(), mois0, annee0, cvv.getText(), email.getText());
                 if(token !=null){
                      try { 
            Charge ch= PaiementStripe.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", cmd.getPrixtotal(),"sk_test_yIqEVjLUzA1vwKhr1PjhnS9I",num.getText(), mois0, annee0, cvv.getText(), email.getText());
    

                        Dialog.show("Succès", "Le paiement a été effectué avec succès" , "OK", null); 
                        
                            CommandeService.getInstance().modcmd(cmd.getId());
                             ProduitForm g = new ProduitForm();
                            g.getF().show();
                            
                        } catch (Exception ex) {
                          
                        }
       
}
 else
 {
     Dialog.show("Erreur", "Merci de vérifier vos informations" , "OK", null); 
 }
                 } });
            c3.add(anuler);    
            c3.add(back);
                f.add(num);
                f.add(mois);
                f.add(annee);
                f.add(cvv) ;
                f.add(email);
                f.add(btnaff);
             

        
f.add(c3);
    }
         public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
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
