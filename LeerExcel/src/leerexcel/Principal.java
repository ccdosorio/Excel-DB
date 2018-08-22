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

/**
 *
 * @author programacion
 */
public class Principal {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception  {

        String fileName = "C:\\Users\\informatica\\Desktop\\movies.xls";

        try {
            ConnectionDB conn = new ConnectionDB();
            conn.connDataBase();
            InputStream myFile = new FileInputStream(new File(fileName));
            HSSFWorkbook wb = new HSSFWorkbook(myFile);
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFCell cell;
            HSSFRow row;
            ArrayList<String> arrayCat = new ArrayList<String>(); 
            System.out.println("!!!!!!!Apunto de entrar a loops!!!!!");

            System.out.println("!!!!!Numero de filas " + sheet.getLastRowNum()+"!!!!");

            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                String cadena_texto =  "";
                
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    cadena_texto += cell.toString();
                }
                String year = "";
                String[] parts = cadena_texto.split("::");
                String[] descandenarYear = parts[1].split("\\(");
                String[] arrayCategory = parts[2].split("\\|");
                
                
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
             
                System.out.println("id: "+parts[0] + " Movie: " + descandenarYear[0] + " Year: " + year); 
                conn.enviarDatos("INSERT INTO pelicula (id, descripcion, year) values ('" + parts[0] + "', \"" + descandenarYear[0]  + "\", '" + year + "')");
                
                for (int j = 0; j < arrayCat.size(); j++) {
                    for (int k = 0;  k < arrayCategory.length; k++) {
                        if(arrayCat.get(j).contains(arrayCategory[k])){
                            conn.enviarDatos("INSERT INTO peliculascategorias (idPelicula, idCategoria) VALUES('" + parts[0] + "', '" + (j + 1) + "')");
                        }
                    }
                }
            }
            System.out.println("!!!!!Finalizado!!!!!");
                for (int l = 0; l < arrayCat.size(); l++) {
                    conn.enviarDatos("INSERT INTO categoria(descripcion) VALUES(\""+arrayCat.get(l)+"\")");
                    System.out.println("Category: "+ arrayCat.get(l));
                }
 
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        
    }

}
