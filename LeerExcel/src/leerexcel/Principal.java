/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
/**
 *
 * @author programacion
 */
public class Principal {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        String fileName = "C:\\Users\\informatica\\Desktop\\movies.xls";        
        
        //CreateExcel(fileName, data);
        ReadExcel(fileName);
        //OverwriteExcel(fileName, data); 
        
    }

    private static void ReadExcel(String fileName) {
        try {
            InputStream myFile = new FileInputStream(new File(fileName));
            HSSFWorkbook wb = new HSSFWorkbook(myFile);
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFCell cell;
            HSSFRow row;

            System.out.println("Apunto de entrar a loops");

            System.out.println("" + sheet.getLastRowNum());

            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                String cadena_texto =  "";
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    cadena_texto += cell.toString();
                }
                System.out.println(cadena_texto);
            }
            System.out.println("Finalizado");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }    
    
}
