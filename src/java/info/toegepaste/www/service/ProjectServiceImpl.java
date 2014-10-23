/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.service;

import info.toegepaste.www.entity.Score;
import info.toegepaste.www.entity.Test;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import info.toegepaste.www.entity.*;
import java.awt.FileDialog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import javax.servlet.http.Part;
import javax.swing.JFrame;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Gert-jan
 */
@Stateless
public class ProjectServiceImpl implements ProjectService {
    @PersistenceContext
    private EntityManager em;
    public ArrayList<String> getExcel() {
        ArrayList<String> lijst = new ArrayList<String>();
        try {

            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Jeroen\\Desktop\\poi-test.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("POI Worksheet");
            HSSFRow row1 = worksheet.getRow(0);
            HSSFCell cellA1 = row1.getCell((short) 0);
            String a1Val = cellA1.getStringCellValue();
            HSSFCell cellB1 = row1.getCell((short) 1);
            String b1Val = cellB1.getStringCellValue();
            HSSFCell cellC1 = row1.getCell((short) 2);
            String c1Val = cellC1.getStringCellValue();
            HSSFCell cellD1 = row1.getCell((short) 3);
            String d1Val = cellD1.getStringCellValue();

            lijst.add(a1Val);
            lijst.add(b1Val);
            lijst.add(c1Val);
            lijst.add(d1Val);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }
    

    public ArrayList<String> upload() {
        Part testFile = null;
        ArrayList<String> lijst = new ArrayList<String>();
        try {

            FileInputStream fileInputStream = new FileInputStream(getFilename(testFile));
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("POI Worksheet");
            HSSFRow row1 = worksheet.getRow(0);
            HSSFCell cellA1 = row1.getCell((short) 0);
            String a1Val = cellA1.getStringCellValue();
            HSSFCell cellB1 = row1.getCell((short) 1);
            String b1Val = cellB1.getStringCellValue();
            HSSFCell cellC1 = row1.getCell((short) 2);
            String c1Val = cellC1.getStringCellValue();
            HSSFCell cellD1 = row1.getCell((short) 3);
            String d1Val = cellD1.getStringCellValue();

            lijst.add(a1Val);
            lijst.add(b1Val);
            lijst.add(c1Val);
            lijst.add(d1Val);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lijst;

    }
        
    @Override
    public String test() {
        return "test";
    }
    
    @Override
    public List<Score> getAllScores() {
        Query q = em.createQuery("SELECT s FROM Score s");
        
        return (List<Score>) q.getResultList();
    }
    
    @Override
    public List<Score> getAllScoresByTest(int id) {
        Query q = em.createQuery("SELECT s FROM Score s where s.test.id = :id");
        q.setParameter("id", id);
        
        return (List<Score>) q.getResultList();
    }
    @Override
    public List<Score> getAllScoresByVak(int id) {
        Query q = em.createQuery("SELECT s FROM Score s where s.test.vak.id = :id");
        q.setParameter("id", id);
        
        return (List<Score>) q.getResultList();
    }
    @Override
    public List<Score> getAllScoresByKlas(int id) {
        Query q = em.createQuery("SELECT s FROM Score s where s.student.klas.id = :id");
        q.setParameter("id", id);
        
        return (List<Score>) q.getResultList();
    }
    @Override
    public List<Score> getAllScoresByStudent(int id) {
        Query q = em.createQuery("SELECT s FROM Score s where s.student.id = :id");
        q.setParameter("id", id);
        
        return (List<Score>) q.getResultList();
    }
    @Override
    public List<Test> getAllTests(){
        Query q = em.createQuery("SELECT t from Test t");
        
        return (List<Test>) q.getResultList();
    }
    @Override
    public List<Vak> getAllVakken(){
        Query q = em.createQuery("SELECT v from Vak v");
        
        return (List<Vak>) q.getResultList();
    }
    @Override
    public List<Student> getAllStudenten(){
        Query q = em.createQuery("SELECT s from Student s");
        
        return (List<Student>) q.getResultList();
    }
    @Override
    public List<Klas> getAllKlassen(){
        Query q = em.createQuery("SELECT k from Klas k");
        
        return (List<Klas>) q.getResultList();
    }
}
