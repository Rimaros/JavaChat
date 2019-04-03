//import ClientWindow.ListSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestClass extends JFrame{

    private static JTextArea chatField;
    private static JPanel contacts;
    private static DefaultListModel<String> dlm;
    private static JScrollPane chatFieldScrollPane;
    private static JPanel operationPanel;
    private static JTextField textMessage;
    private static JButton sendMessage;
    private static ArrayList<String> contactsList2 = new ArrayList<>();

    private TestClass() {

        setBounds(900, 400, 600, 500);
        setTitle("WebMessenger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contactsList2.add("Rimaros");
        contactsList2.add("Nastuha_91");
        contactsList2.add("Goblin");
        contactsList2.add("Goblinus");

        chatField = new JTextArea("\n");
        chatField.setEditable(false);
        chatField.setLineWrap(true);
        chatField.setForeground(Color.white);
        chatField.setFont(new Font("Courier", Font.ITALIC, 12));
        chatField.setBackground(Color.DARK_GRAY);
        chatFieldScrollPane = new JScrollPane(chatField);

        textMessage = new JTextField("Enter Message:   ");
        textMessage.setFont(new Font("Arial", Font.BOLD, 12));


        sendMessage = new JButton("Send");

        operationPanel = new JPanel(new BorderLayout());
        operationPanel.add(sendMessage, BorderLayout.EAST);
        operationPanel.add(textMessage, BorderLayout.CENTER);

        //===================== Представление списка контактов ==========================
        // Модель списка контактов
        dlm = new DefaultListModel<>();
        contactsList2.forEach(dlm::addElement); // добавляем в модель

        // Лист контактов представление по модели
        ArrayList<String> change = new ArrayList<>();

        JList<String> contactsList = new JList<>(dlm);
        contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        System.out.println("я захожу");
        //contactsList.addListSelectionListener(new ListSelectionListener());
        contactsList.setCellRenderer(new SuperDuperListCellRenderer(change));

        change.add("Rimaros");
        change.add("Goblinus");
        change.add("Goblin");


        System.out.println(dlm.get(0));

        // скрол для панели списка контактов
        JScrollPane contactsScrollPane = new JScrollPane(contactsList);

        // панель контактов
        contacts = new JPanel(new BorderLayout());
        contacts.add(contactsScrollPane);          // финальное представление списка контактов
        //===================== Реализация списка контактов ==========================


        add(chatFieldScrollPane, BorderLayout.CENTER); // окно чата с прокруткой
        add(operationPanel, BorderLayout.SOUTH); // панель кнопок
        add(contacts, BorderLayout.WEST); // добавляем панель с контактами на главный фрейм


        // Send Message ======================================================================
        sendMessage.addActionListener(actionEvent -> {
            if(!textMessage.getText().trim().isEmpty()){
                String message = textMessage.getText();
                chatField.append(message + "\n");
                // focus to textMessage
                textMessage.grabFocus();
            }
        });

        // clean message field ===============================================================
        textMessage.addFocusListener( new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                textMessage.setText("");
            }
        });

        Map<String, StringBuilder> contactsMap = new HashMap<String, StringBuilder>();
        contactsMap.put("Rimaros", new StringBuilder(""));
        contactsMap.put("Goblin", new StringBuilder("tuto"));
        contactsMap.put("Tupak", new StringBuilder(""));
        contactsMap.put("Nikitos", new StringBuilder(""));

        String kakanoid = "Glebus";

        System.out.println(contactsMap.get("Goblin"));

        StringBuilder newString;
        newString = contactsMap.get("Goblin").append("\n").append(kakanoid);
        contactsMap.put("Goblin", newString);

        System.out.println(contactsMap.get("Goblin") + "\n");

        if(change.contains("Goblin")) {System.out.println("I am find Goblin!");
            System.out.println(change.indexOf("Goblin"));}
            else System.out.println("I not find");

        setVisible(true);
    }

    public static void main(String[] args) {
        new TestClass();
    }
}


class SuperDuperListCellRenderer extends DefaultListCellRenderer {

    private ArrayList <String> changeList;

    SuperDuperListCellRenderer (ArrayList<String> changeList){
        this.changeList = changeList;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        Component renderComponent = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if(changeList.size() > 0){
            for (String aChangeList : changeList) {
                if (aChangeList.equals(value.toString())) {
                    setBackground(Color.RED);
                }
            }
        }
       return renderComponent;
    }
}






/*
 //static ArrayList<String> answerArray = new ArrayList<>();
    String answer = "message_hello, beatch!";

        answerArray.addAll(Arrays.asList(answer.split("_")));

                System.out.println(answerArray.get(0) + "\n" + answerArray.get(1));
                System.out.println("\n" + answerArray.size());*/



 /* if(isSelected) {
            System.out.println(value + "   " + index);*/
           /* for (int j = 0; j < changeList.size(); j++) {

                if (changeList.get(j) == index) {
                    System.out.println("I in massiv");
                    //renderComponent.setBackground(Color.RED);
                }
            }*/
           /* if(changeList.contains(value.toString())){
                System.out.println("I in massiv");
                System.out.println(value);
            } else changeList.add((String) value);*/
//}
