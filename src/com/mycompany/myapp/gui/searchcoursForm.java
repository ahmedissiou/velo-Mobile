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
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.services.RechercheService;

/**
 *
 * @author amin
 */
public class searchcoursForm extends Form{
    
    
    Form current;
    

    public searchcoursForm(Form previous,String i)  {
        current = this;
 
        current.setTitle("Search : "+i);
        current.setLayout(BoxLayout.y());
        current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });

       
        current.getToolbar().addCommandToSideMenu("cours", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               coursForm events = new coursForm();
                events.getF().show();
            }
        });
     

        

        current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            HomeForm sb = new HomeForm();
            sb.show();
        });

        Container c1 = new Container(BoxLayout.y());
        Image imgUrl;
        for (cours c : RechercheService.getInstance().getAllcours(i)) {

           
            Label label0 = new Label(" ");
            c1.add(label0);
            Label label1 = new Label(" ");
            c1.add(label1);

            Label label2 = new Label(c.getLieu_cours());
            c1.add(label2);
               Label label4 = new Label(c.getDate_cours());
            c1.add(label4);
          Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

           imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + c.getNom_image(), "http://localhost/pifinal/pidevfinal/web/images/" + c.getNom_image());
            ImageViewer img1 = new ImageViewer(imgUrl);


            Button b = new Button("Voir détail");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    détailscours sde = new  détailscours(c);
                    sde.getF().show();

                }
            });

            c1.add(img1);
            c1.add(b);
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