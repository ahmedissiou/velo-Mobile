/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.mycompany.myapp.MyApplication;

import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.CommentService;
import com.mycompany.myapp.services.EventService;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Manel 
 */
public class AfficherDetailEvent  extends Form {
        Form DetailEvent;
         private static int id ;
        Container c4;
        CommentService cmt1;
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

        
        public AfficherDetailEvent (int id) {
         //  c4 = new Container(BoxLayout.y());
            cmt1 = new CommentService();

            this.id = id ;
            TextModeLayout tl = new TextModeLayout(3, 2);
             DetailEvent = new Form(" Detail  evenement ", BoxLayout.y());
                 EventService es = new EventService();
                 Event e =   es.afficherevent(id);
                 Container c1 = new Container(BoxLayout.y());
                  Image imgUrl;
               
                 Label Nomevet = new Label(e.getNomevet());
                 Nomevet.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
                 Label lieuevet = new Label("Lieu : " +e.getLieuevt());
                 Label prixe  = new Label("prix : " +Integer.toString(e.getPrixe()));
                 Label Nbparticipents  = new Label("Nombre des participants :  " +Integer.toString(e.getNbparticipent()));
                 TextArea description= new TextArea(e.getDescription());
                 description.getAllStyles().setFgColor(0x000000);
                 description.setMaxSize(2147483647 );
                 description.setEditable(false);
                 Image placeholder = Image.createImage(500, 120); 
                 EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

           
              imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image(), "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image()).scaled(1200, 600);
                     c1.add(Nomevet);
                     c1.add(imgUrl);
                     c1.addAll(description,lieuevet,prixe,Nbparticipents);
              Container c2 = new Container(BoxLayout.y());
              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                 Container d2 = new Container(BoxLayout.x());
                 Container d1 = new Container(BoxLayout.x());

        final FontImage time = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        
        d1.add(time);
        d1.add(new Label("Date Début : "));
        d1.add(new Label(formatter.format(e.getDate_debe())));
        d2.add(time);
        d2.add(new Label("Date Fin : "));
        d2.add(new Label(formatter.format(e.getDate_fine())));
        c2.add(d1);
        c2.add(d2);
        Container c20 = new Container(BoxLayout.x());

         Button btnedit = new Button("Modifier ");
         Button btndelete= new Button("Supprimer");
         Button btnparticiper= new Button("Participer");
         Button btnannuler= new Button("Annuler");
         Button btnsignaler= new Button("Signaler");
            Dimension d = new Dimension(200,200);
                    //btnedit.setPreferredSize(d);
           if(e.getCreator_id() ==  MyApplication.user.getId()){

         c20.add(btnedit);
         c20.add(btndelete); 
           }
         if (e.getNbparticipent() > 0){
              if (!es.testParticiper(MyApplication.user.getId(), id)){
               c20.add(btnparticiper); 
              }
              else {
               c20.add(btnannuler); 

              }
         }
         if (es.testSignaler(MyApplication.user.getId(), id)){
         c20.add(btnsignaler); }
         c20.setScrollableX(true);
            c2.add(c20);
               


//             Container h= new Container(BoxLayout.x());
//              Button btnmodifiercmt = new Button("modifier  ") ;
//              Button btnajoutercmt = new Button("ajouter   ") ;
               //h.addAll(btnajoutercmt,btnmodifiercmt); 
             // c4.add(h);
                         btnedit.addActionListener((ee)-> {
                          ModifierEvent mdev = new ModifierEvent(e.getId());
                          mdev.getF().show();
            });
                        btndelete.addActionListener((ee1)-> {
                           es.supprimer(id);
                             Dialog.show("Succés", "Evenement supprimé", "ok", null);
                            AfficherAllEvents aff = new AfficherAllEvents();
                            aff.getF().showBack();
                  });

                         btnparticiper.addActionListener((ee)-> {
                          es.participer(id, MyApplication.user.getId());
                          Dialog.show("Succés", "Participation effectuée avec succée ", "ok", null);
                          c20.removeComponent(btnparticiper);
                          c20.add(btnannuler);
       
       
            });
                        btnannuler.addActionListener((ee2)-> {
                          es.annuler(id,  MyApplication.user.getId());
                          Dialog.show("Succés", "Participation annulé avec succée ", "ok", null);
                          c20.removeComponent(btnannuler);
                          c20.add(btnparticiper);
       
            });
                        
                        btnsignaler.addActionListener((ee3)-> {
                          es.SignalerEvent(id,  MyApplication.user.getId());
                          c20.removeComponent(btnsignaler);

                          Dialog.show("Succés", "Evenement signalé  avec succé ", "ok", null);
       
            });
              
            DetailEvent .getToolbar().addCommandToOverflowMenu("Back ", null, evd-> { 
                 AfficherAllEvents aff = new AfficherAllEvents();
                 aff.getF().showBack();
              
         });
                            allComments();
                              Container c5 = new Container(BoxLayout.y());
                             c5.add(new Label("Ajouter votre  commentaire : "));  
                              TextArea cmt= new TextArea();

                Button btnajoutcmt = new Button("Ajouter"); 
                c5.addAll(cmt,btnajoutcmt);
                btnajoutcmt.addActionListener((ee)->{            
                      Comment com = new Comment(cmt.getText(),  MyApplication.user.getId(), id);
                      cmt1.ajoutCmt(com);
                      
                      Dialog.show("Succés", "Commentaire ajouté  avec succé ", "ok", null);
                      AfficherDetailEvent af = new AfficherDetailEvent(id);
                      af.getF().show();
                      } );

         DetailEvent.addAll(c1,c2,c4,c5);
        
        };
        
   
   public void allComments(){
             Container cont = new Container(BoxLayout.y());

             ArrayList<Comment> listComment = new ArrayList<>();
             listComment = cmt1.getAllComments(id); 
               // c4.removeAll();
                cont.add(new Label("Liste des commentaires : ")); 
                   for (Comment c  : listComment) {
                       Container c41 = new Container(BoxLayout.x());
                      Label user = new Label(c.getUser_name());
                      
                      TextField Commentaire  = new TextField(c.getCommentaire());
                      Commentaire.setEditable(false);
                      c41.addAll(user,Commentaire);
                      //c4.add(h);
                      cont.add(c41);
                     Container c40 = new Container(BoxLayout.x());

                  
                     
                     
//                      Button btndeletecmt = new Button() ;
//                      final FontImage deleteIcon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Label", 6);
//                      btndeletecmt.setIcon(deleteIcon);
//                      btndeletecmt.getAllStyles().setBackgroundGradientStartColor(0xffffff);
//                      btndeletecmt.getAllStyles().setBgColor(0xffffff,true);
//                      btndeletecmt.getAllStyles().setBackgroundGradientEndColor(0xffffff);

                      FloatingActionButton btndeletecmt = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE);
                      c40.add(btndeletecmt);

                      FloatingActionButton btnmodifiercmt = FloatingActionButton.createFAB(FontImage.MATERIAL_EDIT);
                      c40.add(btnmodifiercmt);
                      
                      
                    FloatingActionButton btnenregistrercmt = FloatingActionButton.createFAB(FontImage.MATERIAL_SAVE);
         
                      btndeletecmt.addActionListener((ed)-> {
                        cmt1.supprimercmt(c.getId());
                        Dialog.show("Succés", "Commentaire supprimé  avec succé ", "ok", null);
                        AfficherDetailEvent af = new AfficherDetailEvent(id);
                     af.getF().show();
                   
                      });
                      
                      btnmodifiercmt.addActionListener((eg)-> { 
                      Commentaire.setEditable(true);
                      c40.add(btnenregistrercmt);
                      c40.removeComponent(btnmodifiercmt);
                         });
                                       
                      btnenregistrercmt.addActionListener((eg)-> { 
                        c.setCommentaire(Commentaire.getText());
                        
                        cmt1.modifierComment(c);
                         Commentaire.setEditable(false);
                      c40.add(btnmodifiercmt);
                      c40.removeComponent(btnenregistrercmt);
                        Dialog.show("Succés", "Commentaire modifié   avec succées ", "ok", null);
                        
           AfficherDetailEvent af = new AfficherDetailEvent(id);
                      af.getF().show();
                      });
                      
                     if(c.getUser_id() ==  MyApplication.user.getId()){
                  
                      cont.add(c40);
                     }

       /*       if (c4 != null){  
            c4.removeAll();}*/
                }
   
               c4 = cont; 

   }     
  public Form getF() {
        return DetailEvent  ;
    }
     
    
        
}
