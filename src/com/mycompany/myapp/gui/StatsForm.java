/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.servicereparation;

import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Emir
 */
public class StatsForm extends HomeForm{
    public StatsForm(){
            this(com.codename1.ui.util.Resources.getGlobalResources());

    }
     public StatsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
     
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Statistiques", "Title")
                )
        );
        Label l1=new Label("Cliquez ici pour consulter les stats");
         l1.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BudgetPieChart a = new BudgetPieChart();

                a.execute().show();
            }
        });
         add(l1);
        
         servicereparation bs=new servicereparation();
         //System.out.println(bs.getNbrDocValue().toString());
     }
}
