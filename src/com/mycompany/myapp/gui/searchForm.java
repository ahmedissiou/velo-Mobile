/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
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
import com.mycompany.myapp.entities.reparation;
import com.mycompany.myapp.services.RechercheService;
import com.mycompany.myapp.services.ServiceRecherche;

/**
 *
 * @author amin
 */
public class searchForm extends Form{
    
    
    Form current;
    

    public searchForm(Form previous,String i)  {
        current = this;
 
        current.setTitle("Search : "+i);
        current.setLayout(BoxLayout.y());
        current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });

       
        current.getToolbar().addCommandToSideMenu("reparation", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               repForm events = new repForm();
                events.getF().show();
            }
        });
     

        

        current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            HomeForm sb = new HomeForm();
            sb.show();
        });
        
       
       for (reparation c : ServiceRecherche.getInstance().getAllreparation(i)) {
           Container c1= new Container(BoxLayout.y());
     
             c1.add(new Label("Resultat de votre recherche: "));
            

           Container c2 = new Container(BoxLayout.y());
         final FontImage ade = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Label", 6);
             c2.add(ade);
             c2.add(new Label("desc prob : "));
             c2.add(new Label(c.getDescriptionprob()));
            
              
              Container c3 = new Container(BoxLayout.x());
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_TIMER, "Label", 6);
             c3.add(adr);
             c3.add(new Label("date : "));
             c3.add(new Label(formatter.format(c.getDateRep())));
             
             Container c4 = new Container(BoxLayout.x());
             final FontImage adp = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
             c4.add(adp);
             c4.add(new Label("ville : "));
             c4.add(new Label(c.getVille()));
             
              Label label17 = new Label("_______________________________________________");
              
           
       
              
            
              
            
           
           
            current.add(c1);
            c1.add(c2);
            c1.add(c3);
            c1.add(c4);
            c1.add(label17);  
            

            
          //  c1.add(b);
        } 
       
         
        Button b = new Button("Voir detail");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    

                }
            });
        
    }

     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
}
