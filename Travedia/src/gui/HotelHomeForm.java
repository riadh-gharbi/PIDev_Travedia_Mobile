/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author riadh
 */
public class HotelHomeForm extends BaseForm {
    Form current;
    public HotelHomeForm() {
        current=this; //Back 
        setTitle("Hotels");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAjouterHotel = new Button("Ajouter Hotel");
        Button btnListHotel = new Button("Liste des Hotels");
        
    
        
        
        
        btnAjouterHotel.addActionListener(e-> new AddHotelForm(current).show());
        btnListHotel.addActionListener(e-> new ListeHotel(current).show());
        addAll(btnAjouterHotel,btnListHotel);
        
        
    }
    
    
}
