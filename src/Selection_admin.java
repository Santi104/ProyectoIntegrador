import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection_admin extends JFrame {
    private JButton productsButton;
    private JButton appointmentsButton;
    private JButton servicesButton;
    private JButton productsEdit;
    private JButton appointmentsEdit;
    private JButton rolEditButton;
    private JPanel adminPanel;
    private JButton usersButton;
    private JButton petsButton;
    String username;


    public Selection_admin(String user) {
        this.username = user;

        servicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services service = new Services();
                service.openServices();
            }
        });

        appointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Wappointment newAppointment = new Wappointment(username);
                newAppointment.openWindow(username);
            }
        });


        rolEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rolselection rolbutton = new Rolselection();
            }
        });

        appointmentsEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editappointments editappointments = new Editappointments();
                editappointments.openEditAppointments();
            }
        });
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users_section usersSection = new Users_section();
                usersSection.openUsersSection();
            }
        });
        petsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pets_section petsSection = new Pets_section();
                petsSection.openPetsSection();
            }
        });
    }

    public void openAdminSelection() {
        setContentPane(adminPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
