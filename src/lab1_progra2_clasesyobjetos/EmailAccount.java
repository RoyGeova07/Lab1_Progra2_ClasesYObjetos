/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_progra2_clasesyobjetos;
public class EmailAccount {

    
    private String direccionEmail;
    private String contrasena;
    private String nombreUsuario;
    private Correo[] inbox = new Correo[50]; 

    // Constructor
    public EmailAccount(String direccionEmail, String contrasena, String nombreUsuario) {
        this.direccionEmail = direccionEmail;
        this.contrasena = contrasena;
        this.nombreUsuario = nombreUsuario;
    }
//hola
    
    public String getDireccionEmail() {
        return direccionEmail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    
    public boolean recibirCorreo(Correo correo) {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] == null) { 
                inbox[i] = correo;
                return true; 
            }
        }
        return false;
    }

    
    public void printInbox() {
        System.out.println("Email: " + direccionEmail);
        System.out.println("Usuario: " + nombreUsuario);

        int noLeidos = 0;
        int total = 0;

        for (int cont = 0; cont < inbox.length; cont++) {
            if (inbox[cont] != null) {
                String estado = inbox[cont].isLeido() ? "LEIDO" : "SIN LEER";
                System.out.println((cont + 1) + " - " + inbox[cont].getRemitente() + " - " + inbox[cont].getAsunto() + " - " + estado);
                total++;
                if (!inbox[cont].isLeido()) {
                    noLeidos++;
                }
            }
        }

        System.out.println("Correos sin leer: " + noLeidos);
        System.out.println("Total correos: " + total);
    }

    
    public void leerCorreo(int pos) {
        if (pos > 0 && pos <= inbox.length && inbox[pos - 1] != null) {
            System.out.println("De: " + inbox[pos - 1].getRemitente());
            System.out.println("Asunto: " + inbox[pos - 1].getAsunto());
            System.out.println("Mensaje: " + inbox[pos - 1].getCuerpo());
            inbox[pos - 1].setLeido(true); 
        } else {
            System.out.println("Correo no existe");
        }
    }

   
    public void borrarLeidos() {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] != null && inbox[i].isLeido()) {
                inbox[i] = null; 
            }
        }
    }
}


class Correo {
    private String remitente;
    private String asunto;
    private String cuerpo;
    private boolean leido;

    
    public Correo(String remitente, String asunto, String cuerpo) {
        this.remitente = remitente;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.leido = false; 
    }

    
    public String getRemitente() {
        return remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
