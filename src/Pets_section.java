import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Pets_section extends JFrame{
    private JTable tabla;
    private JPanel petsPanel;
    private JTextField IdField;
    private JButton deleteButton;
    private JButton button1;
    Connection conexion;
    DefaultListModel modLista= new DefaultListModel<>();
    String[] columnas = {"id","nombre","apellido","telefono","correo","rol"};
    String[] registros = new String[10];
    DefaultTableModel modTabla = new DefaultTableModel(null , columnas);
    Statement st;
    ResultSet rs;
    PreparedStatement ps;

    public Pets_section() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteSection();
                }catch (SQLException exc) {
                    throw new RuntimeException(exc);
                }
            }
        });
    }

    void conectar() {
        try {
            conexion= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proyectoIntegrador", "root", "Santi104");

        }catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void openPetsSection() {
        setContentPane(petsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        try {
            consultar();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    void consultar() throws SQLException {
        conectar();
        modTabla.setRowCount(0);
        tabla.setModel(modTabla);
        st = conexion.createStatement();
        rs = st.executeQuery("select cod_mascota, nombre, tipo, raza, fecha_nacimiento, tipo from Mascota");
        while (rs.next()) {
            registros[0] = rs.getString("cod_mascota");
            registros[1] = rs.getString("nombre");
            registros[2] = rs.getString("tipo");
            registros[3] = rs.getString("raza");
            registros[4] = rs.getString("fecha_nacimiento");
            registros[5] = rs.getString("tipo");


            modTabla.addRow(registros);
        }
    }

    void deleteSection() throws SQLException {
        ps = conexion.prepareStatement("Delete from Usuarios where id_usuario=?");
        ps.setString(1, IdField.getText());

        if (ps.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null,"Remove User");
            consultar();
        }
    }
}
