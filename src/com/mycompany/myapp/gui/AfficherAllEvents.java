/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.EventService;
import java.util.ArrayList;

/**
 *
 * @author Manel
 */
public class AfficherAllEvents extends Form  {
    Form current;
 public AfficherAllEvents() {
     
    current=this;
        setTitle("Evenement ");
        setLayout(BoxLayout.y());
        current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                  new HomeForm().show();

            }
        });
            
//            current.getToolbar().addCommandToSideMenu("About", null, new ActionListener() {
//             @Override
//            public void actionPerformed(ActionEvent evt) {
//           AboutForm about = new AboutForm();
//           about.getF().show();
//            }
//        });
        EventService es = new EventService();
        ArrayList<Event> listEvent = new ArrayList<>();
        listEvent = es.getAllEvent();
        Container c1 = new Container(BoxLayout.y());
        Image imgUrl;
        Label label = new Label("Liste  des evenements ");
        c1.add(label);
        //////////
        Container s  = new Container(BoxLayout.x());
         Dimension d = new Dimension(900,100);
         TextField search = new TextField("","search");
         FloatingActionButton searchbtn = FloatingActionButton.createFAB(FontImage.MATERIAL_SEARCH);
         search .setPreferredSize(d);
        //Button searchbtn = new Button("Search");
        searchbtn.addActionListener((e) -> {
            String a = search.getText();
        
            new searcheventsForm(current,a).show();
             
        });
        //c1.add(search);
        //c1.add(searchbtn);
        s.addAll(search,searchbtn);
        c1.add(s);
        ///////////
        for (Event e : listEvent) {
          if (e.getIsActive() == true){
             Label Nomevet = new Label(e.getNomevet());
             c1.add(Nomevet);
             Image placeholder = Image.createImage(500, 120); 
             EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

           
             imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image(), "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image()).scaled(1200, 600);
           
             Button btnEnsavoirplus = new Button("En savoir plus ");
             
            c1.add(imgUrl);
           c1.add(btnEnsavoirplus);
 
       btnEnsavoirplus.addActionListener((evv)-> {
      AfficherDetailEvent affd = new AfficherDetailEvent(e.getId()); 
    affd.getF().show();
  //System.out.println(affd);
       });
          }
        }    
        current.add(c1);
        
 current.getToolbar().addCommandToOverflowMenu("Ajouter un evenement ", null, evd-> { 
     AjouterEvent aj = new AjouterEvent();
     aj.getF().show();
         });
 //}
 
 
 current.getToolbar().addCommandToOverflowMenu("Back ", null, evd-> { 
   
    new HomeForm().show();
         });
}
    
    public Form getF() {
        return current;
    }
    
    
}

    
    
