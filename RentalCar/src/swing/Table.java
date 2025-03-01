package swing;

import dao.TenantDAO;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.entity.Tenants;

public class Table extends JTable {


    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable jtable, Object[] o, boolean bln, boolean bln1, int i, int i1) {

                TableHeader header = new TableHeader(o + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable jtable, Object[] o, boolean selected, boolean bln1, int i, int i1) {

                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                if (selected) {
                    com.setForeground(new Color(15, 89, 140));
                } else {
                    com.setForeground(new Color(102, 102, 102));
                }
                return com;
            }

        }
        );
    }

    public void addRow(Object[] row) {
//        while (model.getRowCount() > 0) {
//            model.removeRow(0);
//        }
    DefaultTableModel model = (DefaultTableModel) getModel();

        model.addRow(row);
    }

    public void removeRow() {
            DefaultTableModel model = (DefaultTableModel) getModel();

        model.removeRow(0);
    }

}
