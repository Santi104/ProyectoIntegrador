import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreateUser extends JFrame {
    private JPanel panelUser;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField phoneJfield;
    private JTextField emailJfield;
    private JTextField userJfield;
    private JPasswordField pass;
    private JButton CREATEButton;
    private JButton button1;
    private JTextField direccionField;
    Connection conexion;

    public CreateUser() {
        setContentPane(panelUser);  // Usa el panel inicializado `selectionP`
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();


        CREATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proyectoIntegrador", "root", "Santi104");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addUser() {
        conectar();

        // Obtener los datos del formulario
        String name = firstName.getText();
        String lastname = lastName.getText();
        String phone = phoneJfield.getText();
        String email = emailJfield.getText();
        String user = userJfield.getText();
        String password = new String(pass.getPassword());
        String direccion = direccionField.getText();
        if (name.isEmpty() || lastname.isEmpty() || phone.isEmpty() || email.isEmpty() || user.isEmpty() || password.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos");
        } else {
            String peticionUsuario = "insert into Usuarios (Nombre, Apellido, num_telefono, correo, Usuario, pass) values (?, ?, ?, ?, ?, ?)";
            String peticionCliente = "insert into cliente (nombre, apellido, direccion, telefono, correo) values (?, ?, ?, ?, ?)";

            try {
                PreparedStatement psUsuario = conexion.prepareStatement(peticionUsuario);
                psUsuario.setString(1, name);
                psUsuario.setString(2, lastname);
                psUsuario.setString(3, phone);
                psUsuario.setString(4, email);
                psUsuario.setString(5, user);
                psUsuario.setString(6, password);
                psUsuario.executeUpdate();

                PreparedStatement psCliente = conexion.prepareStatement(peticionCliente);

                psCliente.setString(1, name);
                psCliente.setString(2, lastname);
                psCliente.setString(3, direccion);
                psCliente.setString(4, phone);
                psCliente.setString(5, email);
                psCliente.executeUpdate();

                JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
                dispose();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al crear usuario: " + e.getMessage());
            } finally {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
    }


}
