/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceVelo;
/**
 *
 * @author Hajer
 */
public class AddreclamationForm  extends Form{
       Form  current;
    public AddreclamationForm() {
        current=this;
        setTitle("liste des velos");

         
        setLayout(BoxLayout.y());
        
        current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                  new HomeForm().show();

            }
        });
            
        
      setTitle("Ajouter votre reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Titre");
        TextField tfStatus= new TextField("", "Probleme");
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfStatus.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                      reclamation r= new reclamation(tfStatus.getText(), tfName.getText());
                        if( ServiceReclamation.getInstance().addTask(r))
                            Dialog.show("Success","votre reclamation est envoyée cher client !",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfStatus,btnValider);
        
                
    }

   public Form getF() {
        return current;
    }
     
}