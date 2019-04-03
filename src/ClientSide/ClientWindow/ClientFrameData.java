package ClientWindow;

import javax.swing.*;
import javax.swing.ListCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientFrameData {

    public JTextArea chatField;
    public  JList<String> contactsList;
    JTextField textMessage;
    JButton sendMessage;
    JMenuBar menuBar;
    JPanel operationPanel;
    public JPanel contacts;
    DefaultListModel<String> dlm;
    JScrollPane chatFieldScrollPane;
    public ArrayList<String> waitingContacts;
    public String changedContact;
    public Map<String, StringBuilder> contactsMap = new HashMap<String, StringBuilder>();

    JMenuItem search_by_login;
    JMenuItem search_by_name;
    JMenuItem logout;
    JMenuItem exit;

    public ClientFrameData(){

        // шрифт
        Font font = new Font("Verdana", Font.PLAIN, 11);

        // МенюБар
        this.menuBar = new JMenuBar();
        this.search_by_login = new JMenuItem("Search by login");
        this.search_by_name = new JMenuItem("Search by name");
        this.logout = new JMenuItem("Logout");
        this.exit = new JMenuItem("Exit");
        JMenu contactFind = new JMenu("Search contacts...");
        JMenu mainMenu = new JMenu("Menu");

        // упорядочивание компонентов меню бара
        contactFind.add(this.search_by_name);
        contactFind.add(this.search_by_login);
        mainMenu.add(this.logout);
        mainMenu.add(this.exit);
        mainMenu.add(contactFind);
        this.menuBar.add(mainMenu);

        // текстовые поля
        this.chatField = new JTextArea("\n");
        this.chatField.setEditable(false);
        this.chatField.setLineWrap(true);
        this.chatField.setForeground(Color.white);
        this.chatField.setFont(new Font("Courier", Font.ITALIC, 12));
        this.chatField.setBackground(Color.DARK_GRAY);

        this.textMessage = new JTextField("Enter Message:   ");
        this.textMessage.setFont(new Font("Arial", Font.BOLD, 12));

        // кнопки
        this.sendMessage = new JButton("Send");

        // скрол для текстового поля переписки
        this.chatFieldScrollPane = new JScrollPane(chatField);

        // панель кнопки отправки и поля ввода сообщения
        this.operationPanel = new JPanel(new BorderLayout());
        this.operationPanel.add(sendMessage, BorderLayout.EAST);
        this.operationPanel.add(textMessage, BorderLayout.CENTER);

        //===================== Представление списка контактов ==========================
        // Модель списка контактов формируем, добавляем содержимое в мапу
        this.dlm = new DefaultListModel<>();
        for(int i = 0; i < dlm.size(); i++){
            contactsMap.put(dlm.get(i), new StringBuilder(""));
        }

        // Лист контактов представление по модели
        this.changedContact = null;
        contactsList = new JList<>(this.dlm);
        contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactsList.setCellRenderer(new ListCellRenderer(waitingContacts));
        //contactsList.addListSelectionListener(new ListSelectionListener()); // событие выбора клиента из списка реализовано и так

        // скрол для панели списка контактов
        JScrollPane contactsScrollPane = new JScrollPane(contactsList);

        // панель контактов
        this.contacts = new JPanel(new BorderLayout());
        this.contacts.add(contactsScrollPane);          // финальное представление списка контактов
        //===================== Реализация списка контактов ==========================

        // Присвоение шрифтов
        // меню бар
        this.search_by_login.setFont(font);
        this.search_by_name.setFont(font);
        this.logout.setFont(font);
        this.exit.setFont(font);
        contactFind.setFont(font);
        mainMenu.setFont(font);
    }
}
// надо ввести еще и клиентов