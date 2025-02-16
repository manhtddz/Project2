package swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableActionCellRender extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o , boolean isSelected, boolean bln1, int row, int column) {
        Component com= super.getTableCellRendererComponent(table, 0, isSelected, bln1, row, column);
        PanelAction action = new PanelAction();
        if(isSelected ==false&& row %2==0){
            action.setBackground(Color.WHITE);
        }
        action.setBackground(com.getBackground());
        return action;
    }
    
}
