/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.velo;
import com.mycompany.myapp.services.ServiceVelo;
import java.io.IOException;
import java.io.OutputStream;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.myapp.entities.locationn;
import com.mycompany.myapp.services.ServiceLocation;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
/**
 *
 * @author Hajer
 */


public class ShowDetailsvelo extends Form{
Form DetailsEspeceForm;
    Form f;
    int id_velo;
   velo v= new velo();
    int user_id;

    //int SelectedID;
    public ShowDetailsvelo(int id) {

       Toolbar.setGlobalToolbar(true);
      
        this.id_velo = id;
        f = this;
         setTitle("Details");
/*f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
           listveloForm sb = new listveloForm();
            sb.getF().show();
        });*/
f.getToolbar().addCommandToSideMenu("Back", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
            listvelo  sb =  new listvelo();
             sb.getF().show();
            }
        });


     ServiceVelo es = new  ServiceVelo();

        v = es.getEvent2(id);
DetailsEspeceForm = new Form();
String path =  "file://C:/wamp67/www/images/" + v.getNomImage();
            Image setimg = FileEncodedImage.create(path,100, 100);
            ImageViewer iv = new ImageViewer(setimg);
        Container c1 = new Container(BoxLayout.y());

      
        

        Image imgUrl;
        Image placeholder = Image.createImage(1000, 600);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
         imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage(), "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage());
        ImageViewer img1 = new ImageViewer(imgUrl);
        c1.add(img1);

       

        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, "Label", 6);
        c3.add(adr);
        c3.add(new Label("Nom velo: "));
         c3.add(new Label(v.getNomvelo()));
        Container c8 = new Container(BoxLayout.x());
       c8.add(adr);
        c8.add(new Label("Marque: "));
        c8.add(new Label(v.getMarque()));
        
        Container c6 = new Container(BoxLayout.x());
        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c6.add(prx);
        c6.add(new Label("Prix : "));
        Integer prix = v.getPrix();
        String s1 =  Integer .toString(prix);
        c6.add(new Label(s1));
        c6.add(new Label("DT"));
        Container c88 = new Container(BoxLayout.y());
  Container c4 = new Container(BoxLayout.x());
        final FontImage etat;
        etat = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE_OUTLINE, "Label", 6);
        c4.add(etat);
        c4.add(new Label("Disponibilité: "));
        if (v.getEtat()==0)
        {
            c4.add(new Label("dispo"));
             c88.add(c4);
        }
        else { c4.add(new Label("occupé"));
         c88.add(c4);
        ServiceLocation ess = new ServiceLocation();
       // locationn l2 = new locationn();
           ArrayList<locationn> listloc= new ArrayList<>();
        listloc = ess.getAllvelos(v.getId());
     // l=ess.getlocv(v.getId());
        //ArrayList<locationn> listloc = new ArrayList<>();
       //ystem.out.println( l = es.getAllloc(id));
  for (locationn l2 : listloc) {
        Container c22= new Container(BoxLayout.x());
        final FontImage ad = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        c22.add(ad);
        
         c22.add(new Label("Date fin  : "));
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       // SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
 System.out.println(formatter.format(l2.getDate_fin()));
 System.out.println(l2.getNomlocation());
        c22.add(new Label(formatter.format(l2.getDate_fin())));
//       
        c88.add(c22);
         
         //c4.add(c22);
        //}
        }
           
  
        FloatingActionButton ss = FloatingActionButton.createFAB(FontImage.MATERIAL_CAMERA_ALT);
       // Button ss = new Button("*****");
                ss.addActionListener(e1 -> {
                    Image screenshot = Image.createImage(f.getWidth(), f.getHeight());
                    f.revalidate();
                   f.setVisible(true);
                  f.paintComponent(screenshot.getGraphics(), true);
                    String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "test.png";
                    try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                        ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                    } catch (IOException err) {
                        Log.e(err);
                    }
                });
                
        
  /*Button details = new Button("details");
   String path =  "file://C:/wamp67/www/images/" + v.getNomImage();
            Image setimg = FileEncodedImage.create(path,100, 100);
            ImageViewer iv = new ImageViewer(setimg);
            details.addActionListener(ev -> {
                
                DetailsEspeceForm = new Form(v.getMarque(), BoxLayout.y());
               //  ImageViewer ivv = new ImageViewer(imgUrl);
                   ImageViewer ivv = new ImageViewer(setimg);
                //DetailsEspeceForm.add(ivv);
                DetailsEspeceForm.add(ivv);
                Label ldesc = new Label(v.getNomvelo());
                ldesc.getAllStyles().setFgColor(0x8E2800);
                DetailsEspeceForm.add(ldesc);
                Label ltype = new Label("age ="+v.getAge());
                ldesc.getAllStyles().setFgColor(0xff000);
                DetailsEspeceForm.add(ltype);
                Label feriel = new Label("compteur="+v.getAge());
                ldesc.getAllStyles().setFgColor(0xff000);
                DetailsEspeceForm.add(feriel);
                Label bekri = new Label("prix="+v.getPrix());
                ldesc.getAllStyles().setFgColor(0xff000);
                DetailsEspeceForm.add(bekri);
                Label foufa = new Label("image="+v.getImage());
                ldesc.getAllStyles().setFgColor(0xff000);
                DetailsEspeceForm.add(foufa);
                Label fef = new Label("etat="+v.getEtat());
                ldesc.getAllStyles().setFgColor(0xff000);
                DetailsEspeceForm.add(fef);
                
                 Button se = new Button("****");
                Button ss = new Button("*****");
                ss.addActionListener(e1 -> {
                    Image screenshot = Image.createImage(DetailsEspeceForm.getWidth(), DetailsEspeceForm.getHeight());
                    DetailsEspeceForm.revalidate();
                    DetailsEspeceForm.setVisible(true);
                    DetailsEspeceForm.paintComponent(screenshot.getGraphics(), true);
                    String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "test.png";
                    try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                        ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                    } catch (IOException err) {
                        Log.e(err);
                    }
                });
                DetailsEspeceForm.add(ss);
                 DetailsEspeceForm.add(se);
                //DetailsEspeceForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                DetailsEspeceForm.show();
                //DetailsEspeceForm.getToolbar().addCommandToLeftBar("back",null, ev->{
                //current.show();
                //});
                DetailsEspeceForm.show();
            });*/

      

        Container c5 = new Container(BoxLayout.x());

       
      /*  btnPart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                

                if (MyApplication.user == null) {

                    Dialog.show("Description", "Vous devez connecter pour participer", "OK", null); 

                } else {

                    if (e.getType().equals("Custom")) {

                        if (!exist()) {

                            UserEventService uvs = new UserEventService();
                            int i = uvs.addUserEvent(MyApplication.user.getId(), id);

                            GenarateQR qr = new GenarateQR(i);

                        } else {

                            Dialog.show("Accée réfusée", "Vous avez déjà participé à cet événement!", "OK", null);

                        }

                    } else if (e.getType().equals("places")) {

                        if ((e.getNbPart()) < (e.getNbPlaces())) {

                            if (!exist()) {

                                UserEventService uvs = new UserEventService();
                                int i = uvs.addUserEvent(MyApplication.user.getId(), id);
                                GenarateQR qr = new GenarateQR(i);

                            } else {

                                Dialog.show("Accée réfusée", "Vous avez déjà participé à cet événement!", "OK", null);

                            }

                        } else {

                            Dialog.show("Accée réfusée", "Nous sommes désolé il ya plus de places !", "OK", null);

                        }

                    }

                }
            }
        });

        Button btnPartager = new Button("Partager");
        btnPartager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                
                Share s = new Share();
                s.shareFacebook(e.getImage(),id);

            }
        });
        

        
        c5.add(btnPart);
        c5.add(btnPartager);*/


       // c1.add(c2);
        c1.add(c3);
    c1.add(c8);
        c1.add(c6);
    c1.add(c88);
  //   c1.add(c22);
    c1.add(ss);
  
  // DetailsEspeceForm .add(c1);
        f.add(c1);
        
        
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

 /*   public boolean exist() {

        UserEventService ues = new UserEventService();
        
        

        List<UserEvent> listUV = new ArrayList<>();
        listUV = ues.getListEventsByUserID2(MyApplication.user.getId());

        if (listUV != null) {
            for (UserEvent uv : listUV) {
                if (uv.getEventId() == this.id_event) {
                    return true;
                }
            }
        }
        return false;*/

    }

//} 
//}
//