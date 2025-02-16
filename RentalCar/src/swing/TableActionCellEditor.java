package swing;

import display.event.TableActionEvent;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class TableActionCellEditor extends DefaultCellEditor {
    private final TableActionEvent event;
    
    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event=event;
    }

    

    @Override
    public Component getTableCellEditorComponent(JTable table, Object o, boolean isSelected, int row, int column) {
        PanelAction action = new PanelAction();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
}
