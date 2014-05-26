package GUI;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.geom.*;


public class TButton extends javax.swing.JButton{
	
	  public TButton(/*String text, String name*/)
      {
           /*this.setText(text);
           this.setName(name);*/
         //  this.setBackground(Color.BLACK);
		  
		  String m= this.getText();
		  this.setForeground(Color.WHITE);
		  setColour(m);
          
         
      

		  
		    
		  }

	  public void setColour(String  m){
		  
		  if(m.equals(""))
			  this.setBackground(Color.gray);
		else if(m.equals("2")){
			  this.setBackground(Color.red);
			  this.setForeground(Color.white);
		}else if(m.equals("99")||m.equals("198")||m.equals("396")){
				  this.setBackground(Color.YELLOW);
				  this.setForeground(Color.blue);
	
			  
		}else if(m.equals("1")){
			  this.setBackground(Color.blue);
			  this.setForeground(Color.white);
		}else{
			  this.setBackground(Color.white);
			  this.setForeground(Color.black);
		  }
		  
		this.setText(m); 
	  }
	


}
