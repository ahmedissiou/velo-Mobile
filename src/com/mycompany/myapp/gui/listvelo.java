/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.velo;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceVelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hajer
 */
public class listvelo extends Form{

   Form  current;
public listvelo(){ 
     
    current=this;
        setTitle("liste des velos");

         
        setLayout(BoxLayout.y());
        
        current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                  new HomeForm().show();

            }
        });
            
             
    
        ServiceVelo es = new ServiceVelo();
        ArrayList<velo> listvelo = new ArrayList<>();
        listvelo = es.getAllvelos();
current.getToolbar().addCommandToLeftBar("", null, (ev) -> {
            
                HomeForm hf = new HomeForm();
                hf.getF().show();
           
        });
        Container c1 = new Container(BoxLayout.y());
         Container c11 = new Container(BoxLayout.x());
          FloatingActionButton dispo = FloatingActionButton.createFAB(FontImage.MATERIAL_VISIBILITY);
        Image imgUrl;
       
        //imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage(), "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage());
        
        for (velo v : listvelo) {

            Image placeholder = Image.createImage(500, 120); 
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

           imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage(), "http://localhost/pifinal/pidevfinal/web/images/" + v.getNomImage());
            ImageViewer img1 = new ImageViewer(imgUrl);

            Container c5 = new Container(BoxLayout.x());

         Button b = new Button("Voir détail");

        Button btnPartager = new Button("Louer");
     
            
            
           b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    ShowDetailsvelo sde = new ShowDetailsvelo(v.getId());
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
      
            c1.add(img1);
            c1.add(c5);

        }
       
        
dispo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
     
                    dispo sde = new dispo();
                    sde.getF().show();                
                                               }
            });
                
 
         c11.add(dispo);
        current.add(c11);
        current.add(c1);

        
 
         }


   public Form getF() {
        return current;
    }
     public void setF(Form f) {
        this.current = current;
    }
}