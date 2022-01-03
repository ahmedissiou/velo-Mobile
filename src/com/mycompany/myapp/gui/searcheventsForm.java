/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.RechercheEventService;

/**
 *
 * @author Manel 
 */
public class searcheventsForm extends Form {
     Form current;
    

    public searcheventsForm(Form previous,String i)  {
        current = this;
 
        //current.setTitle("Search : "+i);
        current.setTitle("résultat: " +i );
        current.setLayout(BoxLayout.y());
        current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });

       
        current.getToolbar().addCommandToSideMenu("Event", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               AfficherAllEvents events = new AfficherAllEvents();
                events.getF().show();
            }
        });
     

        

        current.getToolbar().addCommandToOverflowMenu("back", null, (ev) -> {
            //HomeForm sb = new HomeForm();
            //sb.show();
            
             AfficherAllEvents events = new AfficherAllEvents();
                events.getF().show();
        });

        Container c1 = new Container(BoxLayout.y());
        Image imgUrl;
        for (Event c : RechercheEventService.getInstance().getAlleventsRecherche(i)) {

           
            Label label0 = new Label(" ");
            c1.add(label0);
            Label label1 = new Label(" ");
            c1.add(label1);

           Label label2 = new Label(c.getNomevet());
           c1.add(label2);
            Image placeholder = Image.createImage(500, 120);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
             
            imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + c.getNom_image(), "http://localhost/pifinal/pidevfinal/web/images/" + c.getNom_image()).scaled(1200, 600);;
            ImageViewer img1 = new ImageViewer(imgUrl);


            Button btnD = new Button("En savoir plus ");
            btnD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                 
//
   AfficherDetailEvent affd = new AfficherDetailEvent(c.getId()); 
    affd.getF().show();

                }
            });

            c1.add(img1);
            c1.add(btnD);
        }
        current.add(c1);
       
    }

     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
}
