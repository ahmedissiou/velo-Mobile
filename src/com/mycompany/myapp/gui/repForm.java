/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.reparation;
import com.mycompany.myapp.services.servicereparation;
//import com.mycompany.myapp.utils.Share;
import java.util.ArrayList;



/**
 *
 * @author esprit
 */
public class repForm extends Form {
   reparation r = new reparation();
Form f;
 public repForm() {
     
    f = new Form();

        servicereparation sr = new servicereparation();
        ArrayList<reparation> listreparation = new ArrayList<>();
        listreparation = sr.getAllreparation();

        f.setTitle("reparation");

        f.setLayout(BoxLayout.y());
        f.setUIID("formulaire");
        f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });

        

       
        f.getToolbar().addCommandToSideMenu("reparation", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                repForm events = new repForm();
                events.getF().show();
            }
        });
      
      

        f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            HomeForm sb = new HomeForm();
            sb.show();
        });
    
        TextField search = new TextField("","Rechercher");
   
        FloatingActionButton searchbtn = FloatingActionButton.createFAB(FontImage.MATERIAL_SEARCH);
        Container c1 = new Container(BoxLayout.y());
        
       Button b = new Button("remplisez le formulaire");
        b.setUIID("demande");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    b.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          addrepForm s = new addrepForm(r);
           s.getF().show();
            }
        });

                }
            });
            
            
     

           
            c1.add(b);
        
        //RECHERCHE
    

       // Button searchbtn = new Button("Search");
        searchbtn.addActionListener((e) -> {
            String a = search.getText();
        
            new searchForm(f,a).show();
             
        });
        c1.add(search);
        c1.add(searchbtn);
        
           
    
        for (reparation e : listreparation) {
             Container c2 = new Container(BoxLayout.x());
             final FontImage ade = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Label", 6);
             c2.add(ade);
             c2.add(new Label("desc prob : "));
             c2.add(new Label(e.getDescriptionprob()));

             Container c3 = new Container(BoxLayout.x());
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_TIMER, "Label", 6);
             c3.add(adr);
             c3.add(new Label("date : "));
             c3.add(new Label(formatter.format(e.getDateRep())));
             
             
             Container c4 = new Container(BoxLayout.x());
             final FontImage adp = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
             c4.add(adp);
             c4.add(new Label("ville : "));
             c4.add(new Label(e.getVille()));
             
             Container c5 = new Container(BoxLayout.x());
             final FontImage ads = FontImage.createMaterial(FontImage.MATERIAL_DIRECTIONS_BIKE, "Label", 6);
             c5.add(ads);
             c5.add(new Label("Marque : "));
             c5.add(new Label(e.getMarque()));

             
              Label label17 = new Label("_______________________________________________");
              
           
       
              
            
              
            c1.add(label17);  
            //c1.add(label1);
            
            c1.add(c2);
            c1.add(c3);
            c1.add(c4);
            c1.add(c5);
            
            
          
          //  c1.add(label5);
            
            //offline_bolt
        
            
          
          

           
          
           

        }
        f.add(c1);


    
 /*Button btnPartager = new Button("Partager");
        btnPartager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                
                Share s = new Share();
                s.shareFacebook(r.getId());

            }
        });
        c1.add(btnPartager);*/
 }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
