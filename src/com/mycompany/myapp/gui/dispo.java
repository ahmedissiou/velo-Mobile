/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.velo;
import com.mycompany.myapp.services.ServiceVelo;
import java.util.ArrayList;

/**
 *
 * @author Hajer
 */
public class dispo extends Form {
      Form  current;
public dispo(){ 
     
    current=this;
        setTitle("liste des velos");

         
        setLayout(BoxLayout.y());
        
        current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                  new HomeForm().show();

            }
        });
     Container c12 = new Container(BoxLayout.y());
 ServiceVelo es = new ServiceVelo();
        ArrayList<velo> listvelo = new ArrayList<>();
        listvelo = es.getdispovelos();
      
         for (velo v : listvelo) {

            Image placeholder = Image.createImage(10000, 6000); 
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
 //imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage(), "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage());
         String path =  "file://C:/wamp67/www/images/" + v.getNomImage();
            Image setimg = FileEncodedImage.create(path,300, 300);
            ImageViewer img1 = new ImageViewer(setimg);
            //ImageViewer img1 = new ImageViewer(imgUrl);

            Container c5 = new Container(BoxLayout.x());

         Button b = new Button("Voir détail");

        Button btnPartager = new Button("Louer");
     
            
         b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Showdetail sde = new Showdetail(v.getId());
                    sde.getF().show();
            //  show sde = new show(v.getId());
                  //  sde.getF().show();
                }
            });
           btnPartager.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    addlocation sde = new addlocation(v);
                    sde.getF().show();
                
            }
            });
        c5.add(b);
        c5.add(btnPartager);
      
            c12.add(img1);
            c12.add(c5);
//listvelo e = new  listvelo();
          // new listveloForm().getF().show();
           //e.getF().show();
        }
             
          //c11.add(dispo);
        current.add(c12);
        //current.add(c1);
                  
                }       
       
   public Form getF() {
        return current;
    }
     public void setF(Form f) {
        this.current = current;
    }     
}

