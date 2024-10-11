/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_progra2_clasesyobjetos;

import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Email {

    //variables
    private String email;
    private String asunto;
    private String contenido;
    private boolean leido;

    //constructor
    public Email(String Email, String reason, String content) {
        email = Email;
        asunto = reason;
        contenido = content;
        leido = false;
    }

    public String getEmail() {
        return email;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public boolean isLeido() {
        return leido;
    }

    public void leido(boolean read) {
        if (read == false) {
            leido = true;
        } else if (read == true) {
            leido = false;
        }
    }

    public void print() {
        System.out.println("De: " + email + "\nAsunto: " + asunto + "\n" + contenido);
    }
}
