/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_progra2_clasesyobjetos;


import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

/**
 *
 * @author royum
 */
public class EmailAccount {
    
    // Atributos
    private String direccionEmail;
    private String password;
    private String nombreCompleto;
    Email[] inbox;
    private int totalEmails;
    
    // Constructor
    public EmailAccount(String direccionEmail, String password, String nombreCompleto) {
        this.direccionEmail = direccionEmail;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.inbox = new Email[50];  
        this.totalEmails = 0;
    }

    
    public String getDireccionEmail() {
        return direccionEmail;
    }

    public String getPassword() {
        return password;
    }

    
  public String getNombreCompleto() {
        return nombreCompleto;
    }

   
    public boolean recibirEmail(Email em) {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] == null) {
                inbox[i] = em;
                totalEmails++;
                return true; 
            }
        }
        return false;  
    }

 public void printInbox() {
        System.out.println("Cuenta: " + direccionEmail + " - " + nombreCompleto);
        int sinLeer = 0;
        
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] != null) {
                String leidoStr = inbox[i].isLeido() ? "LEIDO" : "SIN LEER";
                if (!inbox[i].isLeido()) {
                    sinLeer++;
                }
                System.out.println((i + 1) + " - " + inbox[i].getEmail() + " - " + inbox[i].getAsunto() + " - " + leidoStr);
            }
        }
        System.out.println("Total de emails recibidos: " + totalEmails);
        System.out.println("Total de emails sin leer: " + sinLeer);
    }

   
    public void leerEmail(int posicion) {
        posicion--;  
        if (posicion >= 0 && posicion < inbox.length && inbox[posicion] != null) {
            inbox[posicion].print();  
            inbox[posicion].leido(true);  
        } else {
            System.out.println("Correo No Existe");
        }
    }

    public void borrarleidos(){
        for (int i = 0; i < inbox.length; i++) {
            if(inbox[i] != null && inbox[i].isLeido()){
                inbox[i] = null;
            }
            
        }
    }
 
}
