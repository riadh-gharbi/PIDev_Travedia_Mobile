/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.Travedia.myapp.ReclamationService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import entities.Reclamation;
import entities.ReclamationReponse;
import java.util.Map;

/**
 *
 * @author riadh
 */
public class ListeReclamationForm extends BaseForm {
    Form current;
    public ListeReclamationForm(Form previous) {
        setTitle("List Reclamation");
        current=this;
        SpanLabel sp = new SpanLabel();
        sp.setText(ReclamationService.getInstance().getAllReclamation().toString());
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        
        for (Map.Entry<Reclamation,ReclamationReponse> entry : ReclamationService.getInstance().getAllReclamation().entrySet())
        {
            MultiButton mb = new MultiButton(entry.getKey().getSujet());
            mb.setTextLine2(entry.getKey().getContenu());
            mb.addActionListener(e -> new AfficherRecForm(current,entry.getKey().getId()).show());
            listRec.add(mb);
        }
        add(listRec);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addSearchCommand(e -> 
        {
            String text= (String)e.getSource();
            if(text==null || text.length()==0)
            {
                //Search Bar Empty
                for(Component cmp : getContentPane())
                {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            }else
            {
                text=text.toLowerCase();
                for (Component cmp: getContentPane())
                {
                    Button mb = (Button)cmp;
                    String line1 = mb.getText();
                    boolean show = line1 != null && line1.toLowerCase().contains(text);
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
            }
        });
    }
    
}
