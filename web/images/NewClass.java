/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appletcode;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.*;

/**
 *
 * @author DIVYA
 */
public class NewClass extends Applet{
  
  private String defaultMessage = "0";

  public void paint(Graphics g) {

    String inputFromPage = this.getParameter("no1");
      String inputFromPage2 = this.getParameter("no2");
       
    if (inputFromPage == null&&inputFromPage2==null)
    {
        
        inputFromPage = defaultMessage;
    }
    else
    {int sum=0;
       sum=Integer.parseInt(inputFromPage)+Integer.parseInt(inputFromPage2);
    g.drawString(""+sum+"", 50, 25);
    }
  }
}
