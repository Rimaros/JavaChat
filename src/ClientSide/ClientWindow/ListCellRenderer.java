package ClientWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListCellRenderer extends DefaultListCellRenderer {

    private ArrayList<String> changeList;

    ListCellRenderer (ArrayList<String> changeList){
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
