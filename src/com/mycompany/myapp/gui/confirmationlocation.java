/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.util.DateUtil.compare;

import com.mycompany.myapp.entities.asurance;
import com.mycompany.myapp.entities.locationn;
import com.mycompany.myapp.services.ServiceAsurance;
import com.mycompany.myapp.services.ServiceLocation;
import static java.lang.System.currentTimeMillis;
import java.util.Date;


import javafx.geometry.Pos;
//import org.controlsfx.control.Notifications;


/**
 *
 * @author Hajer
 */
public class confirmationlocation extends Form{
        Form f;
    int id_velo;
   locationn l  = new locationn();
    int user_id;
    asurance a = new asurance();
Date da ;
    //int SelectedID;
    public confirmationlocation(int id)  {

       Toolbar.setGlobalToolbar(true);
       
        this.id_velo = id;
        f = this;
         setTitle("Location");
/*f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
           listveloForm sb = new listveloForm();
            sb.getF().show();
        });*/
f.getToolbar().addCommandToSideMenu("home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           new HomeForm().show();
            }
        });


     ServiceLocation es = new  ServiceLocation();

        l = es.getEvent2(id);

        Container c1 = new Container(BoxLayout.y());

       // Label label = new Label(l.getNomlocation());
        //c1.add(label);
        

      Container c2= new Container(BoxLayout.x());
        final FontImage ad = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        c2.add(ad);
        
         c2.add(new Label("Date debut : "));
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        
        c2.add(new Label(formatter.format(l.getDate_debut())));
         Container c9= new Container(BoxLayout.x());
        final FontImage ad5 = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        c9.add(ad5);
        
         c9.add(new Label("Date fin  : "));
      
    
         c9.add(new Label(formatter2.format(l.getDate_fin())));

        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, "Label", 6);
        c3.add(adr);
        c3.add(new Label("Nom : "));
         c3.add(new Label(l.getNomlocation()));
        
        
        
        Container c6 = new Container(BoxLayout.x());
        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c6.add(prx);
        c6.add(new Label("Prix : "));
        Integer prix = l.getPrixloc();
        String s1 =  Integer .toString(prix);
        c6.add(new Label(s1));
        c6.add(new Label("DT"));
   Integer idloc = l.getId();
    
/*Container c4 = new Container(BoxLayout.x());
final FontImage descimg = FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, "Label", 6);
c4.add(descimg);
c4.add(new Label("Description : "));
Label desc = new Label("voir description ...");
String s = v.getMarque();

desc.addPointerPressedListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent evt) {

Dialog.show("Description", s, "OK", null);

}
});

c4.add(desc);*/

Container c5 = new Container(BoxLayout.x());

Button btnPart = new Button("Afficher prix");
Button c = new Button("Annuler");
btnPart.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        ServiceLocation es = new  ServiceLocation();
        
        es.PayOrder(l.getId());
        
        confirmationlocation sde = null;
        
            sde = new confirmationlocation(l.getId());
        
            
        
        sde.getF().show();
        
    }
});

Container c7 = new Container(BoxLayout.x());
Container c10 = new Container(BoxLayout.x());
Container c11 = new Container(BoxLayout.x());
Button ui = new Button("afficher assurance");
ui.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        ServiceAsurance es = new ServiceAsurance();
        System.out.println(l.getVelo_id());
        a = es.getEvent2(l.getVelo_id());
        Integer p= a.getMontant();
        System.out.println(p);
        String s2 =  Integer .toString(p);
        c10.add(new Label("assurance montant : "));
        c10.add(new Label(s2));
        c10.add(new Label("DT"));
        f.revalidate();
        
    }
});
Button e = new Button("Modifier");

e.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        ServiceLocation es = new  ServiceLocation();
        
        // es.edit(l,l.getId());
        
        Editlocation sde = new  Editlocation(l.getId());
        sde.getF().show();
        
        
        
        
        
    }
});
Button z = new Button("payer");

z.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        ServiceLocation es = new  ServiceLocation();
        
        // es.edit(l,l.getId());
        
       Paiementlocation sde = new  Paiementlocation(l);
        sde.getF().show();
        
        
        
        
        
    }
});

c.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        ServiceLocation es = new  ServiceLocation();
        es.remove(l.getId());
        
        Dialog.show("Success","votre location est annulée!",new Command("OK"));
        
        listvelo sde = new listvelo();
        sde.getF().show();
        
        
        
        
    }
});

//DetailsEspeceForm.getToolbar().addCommandToLeftBar("back",null, ev->{
//current.show();
//});
/*   c7.add(new Label("assurance montant : "));
ServiceAsurance as = new ServiceAsurance();
a = as.getEvent2(l.getVelo_id());

Integer p= a.getMontant();
System.out.println(p);
String s2 =  Integer .toString(p);
Label left = new Label(s2);
left.setVisible(false);
c7.add(new Label(s2));
c7.add(new Label("DT"));*/

/*  btnPart.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent evt) {



if (MyApplication.user == null) {

Dialog.show("Description", "Vous devez connecter pour participer", "OK", null);

} else {

if (e.getType().equals("Custom")) {

if (!exist()) {

UserEventService uvs = new UserEventService();
int i = uvs.addUserEvent(MyApplication.user.getId(), id);

GenarateQR qr = new GenarateQR(i);

} else {

Dialog.show("Accée réfusée", "Vous avez déjà participé à cet événement!", "OK", null);

}

} else if (e.getType().equals("places")) {

if ((e.getNbPart()) < (e.getNbPlaces())) {

if (!exist()) {

UserEventService uvs = new UserEventService();
int i = uvs.addUserEvent(MyApplication.user.getId(), id);
GenarateQR qr = new GenarateQR(i);

} else {

Dialog.show("Accée réfusée", "Vous avez déjà participé à cet événement!", "OK", null);

}

} else {

Dialog.show("Accée réfusée", "Nous sommes désolé il ya plus de places !", "OK", null);

}

}

}
}
});

Button btnPartager = new Button("Partager");
btnPartager.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent evt1) {

Share s = new Share();
s.shareFacebook(e.getImage(),id);

}
});



c5.add(btnPart);
c5.add(btnPartager);*/


// c1.add(c2);

c5.add(btnPart);
c5.add(e);

c7.add(ui);
c11.add(z);
c11.add(c);
c1.add(c3);

c1.add(c6);
c1.add(c2);
c1.add(c9);
c1.add(c10);
c1.add(c5);
c1.add(c7);
c1.add(c11);
f.add(c1);
            }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    }
    