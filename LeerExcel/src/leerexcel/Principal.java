/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Christian
 */
public class Principal {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception  {

        String fileName = "C:\\Users\\cosor\\Desktop\\Practicas\\Primer Lab\\Excel-DB\\LeerExcel\\movies.xls";

        try {
            ConnectionDB conn = new ConnectionDB();
            conn.connDataBase();
            InputStream myFile = new FileInputStream(new File(fileName));
            HSSFWorkbook wb = new HSSFWorkbook(myFile);
            HSSFSheet sheet = wb.getSheetAt(0);
            
            HSSFCell cell;
            HSSFRow row;
            
            String[] parts;
            String[] arrayCategory;
            boolean flag = false;
            
            ArrayList<String> arrayCat = new ArrayList<String>(); 
            System.out.println("¡Leyendo la datos de la pelicula...!");

            System.out.println("!!!!!Numero de filas: " + sheet.getLastRowNum() + "!!!!");
            
            conn.iniciarTrans();
            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                String cadena_texto =  "";
                
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    cadena_texto += cell.toString();
                }
                
                String year = "";
                parts = cadena_texto.split("::");
                String[] descandenarYear = parts[1].split("\\(");
                arrayCategory = parts[2].split("\\|");
                
                if(descandenarYear.length > 3){
                    descandenarYear[0] += ("(" + descandenarYear[1] + "(" + descandenarYear[2]);
                    year = descandenarYear[3].split("\\)")[0];

                }else if(descandenarYear.length > 2){
                    descandenarYear[0] += ("(" + descandenarYear[1]);
                    year = descandenarYear[2].split("\\)")[0];
                }else{
                    year = descandenarYear[1].split("\\)")[0];
                }
                
                for (int j = 0; j < arrayCategory.length; j++) {
                    if (arrayCat.isEmpty()) {
                        arrayCat.add(arrayCategory[j]);
                    }else{
                        for (int k = 0; k < arrayCat.size(); k++) {
                            if (!(arrayCat.contains(arrayCategory[j]))) {
                                arrayCat.add(arrayCategory[j]);
                            }
                        }
                    }
                }
                
                System.out.println("id: "+ parts[0] + " Movie: " + descandenarYear[0] + " Year: " + year); 
                conn.enviarDatosPelicula(Integer.parseInt(parts[0]), descandenarYear[0], Integer.parseInt(year));
                
                if(i == sheet.getLastRowNum()){
                    if(arrayCat.size() == 18){
                        for (int l = 0; l < arrayCat.size(); l++) {
                           conn.enviarDatosCategoria(arrayCat.get(l));
                        }
                        
                    }
                }
                
            }
            
            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                String cadena_texto =  "";
                
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    cadena_texto += cell.toString();
                }
                
                parts = cadena_texto.split("::");
                arrayCategory = parts[2].split("\\|");
                for (String c: arrayCategory) {
                    conn.enviarDatosDualTable(Integer.parseInt(parts[0]), conn.traerCategoria(c)); 
                }
                
            }
            conn.finalizarCommit();
            System.out.println("");
            System.out.println("Data de Movies Insertada");
            System.out.println("");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

}
