package com.dds.real;

import com.dds.DAO.CommunicationUnitDAO;
import com.dds.model.CommunicationUnit;
import com.dds.model.MessageItem;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.junit.*;

public class TabelaTest {

    @Test
    public void testTabela02() throws FileNotFoundException, IOException {
        CommunicationUnitDAO communicationUnitDAO = new CommunicationUnitDAO();
        HashMap<String, String> grupos = new HashMap<>();
        grupos.put("GADDCHC2H", "grupo1");
        grupos.put("GACJ9DY2C", "grupo2");
        grupos.put("GAD6DDH7V", "grupo3");
        grupos.put("GAE68NK3Q", "grupo4");
        grupos.put("GACJAJZNU", "grupo5");

        for (Map.Entry<String, String> grupo : grupos.entrySet()) {

            String filename = "/tmp/tabela-" + grupo.getValue() + ".xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(grupo.getValue());

            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("m/d/yy h:mm"));

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Colaborador ID");
            rowhead.createCell(1).setCellValue("Unidade de comunicação ID");
            rowhead.createCell(2).setCellValue("ID da Mensagem");

            HSSFCell rowDate = rowhead.createCell(3);
            rowDate.setCellStyle(cellStyle);
            rowDate.setCellValue("Data/Hora");

            rowhead.createCell(4).setCellValue("Conteúdo Mensagem");

            int i = 1;
            for (CommunicationUnit c : communicationUnitDAO.findAll()) {
                if (c.getAlternativeId().equals(grupo.getKey())) {
                    List<MessageItem> messagesAll = c.getMessages();
                    Collections.sort(messagesAll, new Comparator<MessageItem>() {
                        @Override
                        public int compare(MessageItem m1, MessageItem m2) {
                            return m1.getDatetime().compareTo(m2.getDatetime());
                        }
                    });
                    for (MessageItem m : messagesAll) {
                        if (m.getCollaborator() != null) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String date = dateFormat.format(m.getDatetime().getTime());

                            HSSFRow row = sheet.createRow(i);
                            row.createCell(0).setCellValue(m.getCollaborator().getId());
                            row.createCell(1).setCellValue(c.getId());
                            row.createCell(2).setCellValue(m.getId());
                            row.createCell(3).setCellValue(date);
                            row.createCell(4).setCellValue(m.getContent().getContent());

                            i++;
                        }
                    }
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
        }
    }

}
