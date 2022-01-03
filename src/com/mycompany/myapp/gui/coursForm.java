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
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.entities.paiement;
import com.mycompany.myapp.services.coursService;
import java.util.ArrayList;



/**
 *
 * @author esprit
 */
public class coursForm extends Form {
     paiement r = new paiement();
    int cour_id;
   cours e = new cours();
Form f;
 public coursForm() {
     
    f = new Form();

        coursService es = new coursService();
        ArrayList<cours> listcours = new ArrayList<>();
        listcours = es.getAllcours();

        f.setTitle("cours");

        f.setLayout(BoxLayout.y());
        f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });

        

       
        f.getToolbar().addCommandToSideMenu("cours", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                coursForm events = new coursForm();
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
        Image imgUrl;
      
        
        //RECHERCHE
    

       // Button searchbtn = new Button("Search");
        searchbtn.addActionListener((e) -> {
            String a = search.getText();
        
            new searchcoursForm(f,a).show();
             
        });
        c1.add(search);
        c1.add(searchbtn);
        
           Container c2 = new Container(BoxLayout.x());
        //EVENTS' LIST
        for (cours e : listcours) {

            Label label0 = new Label(" ");
            c1.add(label0);
            Label label1 = new Label(" ");
              Label label2 = new Label(e.getLieu_cours());
            c1.add(label1);
            c1.add(label2);
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        
          
           
            Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            imgUrl = URLImage.createToStorage(encImage, "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image(), "http://localhost/pifinal/pidevfinal/web/images/" + e.getNom_image());
            ImageViewer img1 = new ImageViewer(imgUrl);
            
          
          

            Button b = new Button("Voir détail");
            Button x = new Button("Participer");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    détailscours sde = new détailscours(e);
                    sde.getF().show();

                }
            });
             x.addActionListener(new ActionListener() {
              @Override
                public void actionPerformed(ActionEvent evt1) {
                      if (e.getNb()<15) {    
            if  (Dialog.show("cours disponible", "vous allez payer : "+ e.getPrixcours()+"Dinars!", "confirmer","annuler")){
         // coursForm z = new coursForm();
              
                 Addpaiement z = new Addpaiement(e.getId());

              
                    z.getF().show();}}
                      
else if (e.getNb()>=15){
             Dialog.show("alert ", "Cours n'est plus disponible  !", new Command("OK"));}

            
     }});

            c1.add(img1);
            c1.add(b);
            c1.add(x);
           

        }
        f.add(c1);

//           cours ee = new cours("za3ma", "description", "image", "type", "adress", 0, 0, 0);
//           es.addEvent(ee);
//            ShowBrand a = new ShowBrand();
//            a.getF().show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
