package ClientWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import Client.AuthenticationDate;
import Client.WorkingFrame;

public class ClientFrame extends JFrame {

    public ClientFrame(AuthenticationDate authenticationDate, ClientWindow.ClientFrameOperations clientFrameOperations, ClientFrameData clientFrameData, WorkingFrame workingFrame) throws IOException {

        // Form =================================================================================
        setBounds(900, 400, 600, 500);
        setTitle("WebMessenger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(clientFrameData.menuBar);

        add(clientFrameData.chatFieldScrollPane, BorderLayout.CENTER); // окно чата с прокруткой
        add(clientFrameData.operationPanel, BorderLayout.SOUTH); // панель кнопок

        // Список контактов (тут проверить)
        clientFrameOperations.getDataAboutContacts(authenticationDate); // получаем список контактов из базы
        authenticationDate.getContacts().forEach(clientFrameData.dlm::addElement); // добавляем в модель
        add(clientFrameData.contacts, BorderLayout.WEST); // добавляем панель с контактами на главный фрейм

        // exit from Program ====================================================================
        clientFrameData.exit.addActionListener(e -> System.exit(0));

        // logout from account ==================================================================
        clientFrameData.logout.addActionListener(e -> {
            workingFrame.changeFrame("helloFrame"); //setVisible(false);
        });

        // search contacts ======================================================================
        clientFrameData.search_by_login.addActionListener(e -> {
            String operationID = "login";
            if(clientFrameOperations.searchClient(operationID, authenticationDate)){
                repaint();
            }
        });

        clientFrameData.search_by_name.addActionListener(e -> {
            String operationID = "name";
            if(clientFrameOperations.searchClient(operationID, authenticationDate)){
                repaint();
            }
        });

        // change client from list
        clientFrameData.contactsList.addListSelectionListener(listSelectionEvent -> {
            int selected = ((JList<?>)listSelectionEvent.getSource()).getSelectedIndex();
            clientFrameData.changedContact = clientFrameData.dlm.get(selected);
            clientFrameData.chatField.setText("");
        });

        // Send Message ======================================================================
        clientFrameData.sendMessage.addActionListener(actionEvent -> {
            if(!clientFrameData.textMessage.getText().trim().isEmpty() && clientFrameData.changedContact != null){
                String operationID = "message_" + clientFrameData.changedContact;
                String message = clientFrameData.textMessage.getText();
                clientFrameData.chatField.append(clientFrameData.changedContact + ":   " + message + "\n");
                clientFrameOperations.sendMessage(authenticationDate, operationID, message);
                // focus to textMessage
                clientFrameData.textMessage.grabFocus();
            }
        });

        // clean message field ===============================================================
        clientFrameData.textMessage.addFocusListener( new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                clientFrameData.textMessage.setText("");
            }
        });

        // close window ======================================================================
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                try {
                    authenticationDate.getOut().println("quit");
                    authenticationDate.getOut().flush();
                    authenticationDate.getOut().close();
                    authenticationDate.getSocket().close();
                } catch (IOException ignored) {
                    ignored.printStackTrace();
                }
            }
        });
        setVisible(false);
    }
}


/*

    ReadThread readThread = new ReadThread(authenticationDate.in, clientFrameData.chatField);
    Thread rthStart = new Thread(readThread);
        rthStart.start();*/
