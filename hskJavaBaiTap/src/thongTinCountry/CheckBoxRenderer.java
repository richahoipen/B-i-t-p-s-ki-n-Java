package thongTinCountry;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CheckBoxRenderer extends DefaultTableCellRenderer 
{
    private static final long serialVersionUID = 1L;
    private JCheckBox checkBox;
 
    Country c;
    public CheckBoxRenderer(boolean demo) 
    {
        checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        if(demo==true)
        {
        	checkBox.setSelected(true);
        }
        if(demo==false)
        {
        	checkBox.setSelected(false);
        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return checkBox;
    }
}
