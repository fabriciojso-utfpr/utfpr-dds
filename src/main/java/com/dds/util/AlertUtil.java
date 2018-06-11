package com.dds.util;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AlertUtil {

    public static boolean confirm() {
        Object[] options = {"Confirmar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, "Clique Confirmar para continuar", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        return result == 0;
    }
    
    public static void alert(String msg) {
       JOptionPane.showMessageDialog(null, msg);
    }
    

    
    

    public static Integer getIdFromTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int column = 0;
        int row = table.getSelectedRow();
        Integer value = (Integer) table.getModel().getValueAt(row, column);
        return value;
    }
}
