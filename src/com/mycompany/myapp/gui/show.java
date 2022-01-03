/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServiceVelo;

/**
 *
 * @author Hajer
 */
public class show extends Form{
    
    Form f;
int id_velo;
    public show (int id) {
        setTitle("List tasks");
        this.id_velo = id;
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceVelo.getInstance().getEvent2(id).toString());
        add(sp);
        ;
    }
   public Form getF() {
        return f;
    }
  
    }
    
    

    

