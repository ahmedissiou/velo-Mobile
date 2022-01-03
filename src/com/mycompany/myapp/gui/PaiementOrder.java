package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import static com.codename1.io.Log.e;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.PaiementStripe;
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.entities.paiement;



import com.stripe.model.Charge;
import com.stripe.model.Token;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author berrahal
 */
public class PaiementOrder extends Form{
    int cour_id;
    int id;
   cours e = new cours(id);
  
Form f;
 
    Container c3 = new Container(BoxLayout.x());
  

    
      FloatingActionButton btnaff = FloatingActionButton.createFAB(FontImage.MATERIAL_CHECK );
 
   
    
       FloatingActionButton anuler = FloatingActionButton.createFAB(FontImage.MATERIAL_CANCEL);
     
   
    public PaiementOrder(cours cours) {
         
    
     
    
    
        
      
          anuler.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                 HomeForm h = new HomeForm();
                    h.getF().show();

            }  });
         
       
           
            
            
        
  
        
               final FontImage back = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,"Label", 6);
                 final FontImage pay = FontImage.createMaterial(FontImage.MATERIAL_PAYMENT, "Label", 11);
     
       final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 11);
          final FontImage bike = FontImage.createMaterial(FontImage.MATERIAL_DIRECTIONS_BIKE, "Label", 11);
        f = new Form("Confirmez votre participation" );
         //f.setUIID("formss");
                  f.add(prx);
                      f.add(bike);
                      f.add(pay);

      TextField num = new TextField("", "Numéro de carte" ) ;
     
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
            Charge ch= PaiementStripe.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", 140 ,"sk_test_yIqEVjLUzA1vwKhr1PjhnS9I",num.getText(), mois0, annee0, cvv.getText(), email.getText());
    

                        Dialog.show("Succès", "Le paiement a été effectué avec succès" , "OK", null); 
                       
      
  
     
     
 }
 else
 {
     Dialog.show("Erreur", "Merci de vérifier vos informations" , "OK", null); 
 }
                 } });
            c3.add(anuler);    
           // c3.add(back);
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
