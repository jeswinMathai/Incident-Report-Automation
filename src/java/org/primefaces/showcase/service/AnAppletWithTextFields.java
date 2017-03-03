/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.showcase.service;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "applet")
@ApplicationScoped

public class AnAppletWithTextFields extends Applet implements ActionListener {
	public void init() {
        }

	public void actionPerformed(ActionEvent e) {
		String temp = textField1.getText();
                String temp2 = textField2.getText();
                System.out.println(temp);
                System.out.println(temp2);
		textField1.setText(textField2.getText());
		textField2.setText(temp);
	}

	TextField textField1, textField2;

    public TextField getTextField1() {
        return textField1;
    }

    public void setTextField1(TextField textField1) {
        this.textField1 = textField1;
    }

    public TextField getTextField2() {
        return textField2;
    }

    public void setTextField2(TextField textField2) {
        this.textField2 = textField2;
    }
        
}
	