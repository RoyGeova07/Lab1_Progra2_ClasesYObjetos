/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab1_progra2_clasesyobjetos;

import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.*;

/**
 *
 * @author royum
 */
public class JavaLook {

    private JFrame frame;
    EmailAccount[] emails = new EmailAccount[50];
    EmailAccount accountActual = null;

    public JavaLook() {
        frame = new JFrame();
        frame.setTitle("JavaLook");
        frame.setSize(500, 300);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel PanelMenu = new JPanel();
        PanelMenu.setLayout(null);
        PanelMenu.setBackground(Color.LIGHT_GRAY);

        JButton logInButton = new JButton("LOGIN");
        logInButton.setBackground(Color.red);
        logInButton.setBounds(150, 50, 200, 40);
        PanelMenu.add(logInButton);

        JButton crearCuenta = new JButton("Create Account");
        crearCuenta.setBackground(Color.DARK_GRAY);
        crearCuenta.setBounds(150, 110, 200, 40);
        PanelMenu.add(crearCuenta);

        JButton salir = new JButton("Salir");
        salir.setBackground(Color.yellow);
        salir.setBounds(150, 170, 200, 40);
        PanelMenu.add(salir);

        frame.add(PanelMenu);
        frame.setVisible(true);

        logInButton.addActionListener(e -> mostrarLogin());
        crearCuenta.addActionListener(e -> mostrarCrearCuenta());
        salir.addActionListener(e -> System.exit(0));
    }

    private void mostrarCrearCuenta() {
        JTextField textoGmail = new JTextField();
        JTextField TextoNombre = new JTextField();
        JTextField TextoUsername = new JTextField();
        JPasswordField textoContrasena = new JPasswordField();
        Object[] mensaje = {"Email:", textoGmail, "Nombre Completo:", TextoNombre, "Nombre de Usuario:", TextoUsername, "Password:", textoContrasena};

        int opciones = JOptionPane.showConfirmDialog(frame, mensaje, "Crear Cuenta", JOptionPane.OK_OPTION);
        if (opciones == JOptionPane.OK_OPTION) {
            String gmail = textoGmail.getText();
            String nombre = TextoNombre.getText();
            String usuario = TextoUsername.getText();
            String contrasena = new String(textoContrasena.getPassword());

            if (buscar(gmail) == null) {
                if (espacio() != -1) {
                    emails[espacio()] = new EmailAccount(gmail, contrasena, nombre);
                    accountActual = buscar(gmail);
                    JOptionPane.showMessageDialog(frame, "Cuenta creada exitosamente");
                    mostrarMain();
                } else {
                    JOptionPane.showMessageDialog(frame, "No hay más espacio para cuentas nuevas");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Error: El email ya existe");
            }
        }
    }

    private void mostrarMain() {
        String[] opcionesBotones = {"Ver Inbox", "Mandar Correo", "Leer un Correo", "Limpiar mi Inbox", "Cerrar Sesión"};
        
        while (true) {
            String option = (String) JOptionPane.showInputDialog(
                    frame,
                    "Seleccione una opción:",
                    "Menú Principal  Usuario en sesión: "+ accountActual.getNombreCompleto(),
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesBotones,
                    opcionesBotones[0]);

            if (option == null) { // Si el usuario cierra el menú o selecciona Cancelar
                JOptionPane.showMessageDialog(frame, "Debes cerrar sesio primero para salir del Menu Principal","Advertencia",JOptionPane.WARNING_MESSAGE);
                continue;
            }
            switch (option) {
                case "Ver Inbox":
                    mostrarInbox();
                    break;
                case "Mandar Correo":
                    enviarEmail();
                    break;
                case "Leer un Correo":
                    leerEmail();
                    break;
                case "Limpiar mi Inbox":
                    accountActual.borrarleidos();
                    JOptionPane.showMessageDialog(frame, "Inbox limpiado exitosamente!");
                    break;
                case "Cerrar Sesión":
                    accountActual = null;
                    JOptionPane.showMessageDialog(frame, "Sesión cerrada.");
                    return;
            }
        }
    }

    private void mostrarInbox() {
        JTextArea textArea = new JTextArea(15, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        if (accountActual != null) {
            StringBuilder inboxContent = new StringBuilder();
            for (int i = 0; i < accountActual.inbox.length; i++) {
                Email email = accountActual.inbox[i];
                if (email != null) {
                    inboxContent.append((i + 1)).append(". ").append(email.getEmail()).append(" - ").append(email.getAsunto()).append("\n");
                }
            }
            textArea.setText(inboxContent.toString());
        } else {
            textArea.setText("No hay correos.");
        }

        JOptionPane.showMessageDialog(frame, scrollPane, "Inbox", JOptionPane.INFORMATION_MESSAGE);
    }

    private void enviarEmail() {
        JTextField emailDestino = new JTextField();
        JTextField asunto = new JTextField();
        JTextArea contenido = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(contenido);
        Object[] mensaje = {"Email destino:", emailDestino, "Asunto:", asunto, "Contenido:", scrollPane};

        int opciones = JOptionPane.showConfirmDialog(frame, mensaje, "Enviar Email", JOptionPane.OK_OPTION);
        if (opciones == JOptionPane.OK_OPTION) {
            String destino = emailDestino.getText().trim();
            String subject = asunto.getText().trim();
            String body = contenido.getText().trim();

            EmailAccount destinatario = buscar(destino);
            if (destinatario != null) {
                Email nuevoEmail = new Email(accountActual.getDireccionEmail(), subject, body);
                destinatario.recibirEmail(nuevoEmail);
                JOptionPane.showMessageDialog(frame, "Correo enviado exitosamente!");
            } else {
                JOptionPane.showMessageDialog(frame, "El destinatario no existe.");
            }
        }
    }

    private void leerEmail() {
        String input = JOptionPane.showInputDialog(frame, "Ingrese el número del correo que desea leer:");
        try {
            int index = Integer.parseInt(input);
            accountActual.leerEmail(index);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Entrada no válida.");
        }
    }

    private void mostrarLogin() {
        JTextField textoGmail = new JTextField();
        JPasswordField textoContrasena = new JPasswordField();
        Object[] mensaje = {"Email:", textoGmail, "Contraseña:", textoContrasena};

        int opciones = JOptionPane.showConfirmDialog(frame, mensaje, "LOGIN", JOptionPane.OK_CANCEL_OPTION);
        if (opciones == JOptionPane.OK_OPTION) {
            String gmail = textoGmail.getText().trim();
            String contra = new String(textoContrasena.getPassword());

            if (login(gmail, contra)) {
                JOptionPane.showMessageDialog(frame, "Sesión iniciada con exito");
                accountActual = buscar(gmail);
                mostrarMain();
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.");
            }
        }
    }

    // Métodos de búsqueda, login y espacio
    private EmailAccount buscar(String email) {
        for (EmailAccount cuenta : emails) {
            if (cuenta != null && cuenta.getDireccionEmail().equalsIgnoreCase(email)) {
                return cuenta;
            }
        }
        return null;
    }

    private boolean login(String email, String password) {
        EmailAccount cuenta = buscar(email);
        return cuenta != null && cuenta.getPassword().equals(password);
    }

    private int espacio() {
        for (int i = 0; i < emails.length; i++) {
            if (emails[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JavaLook());
    }
}
