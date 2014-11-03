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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import javax.faces.application.FacesMessage;
import javax.servlet.http.Part;
import javax.swing.JFrame;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author Gert-jan
 */
@Stateless
public class ProjectServiceImpl implements ProjectService {
    @PersistenceContext
    private EntityManager em;

    
    public IngelezenFile getExcelScores(InputStream fs) {
        ArrayList<String> lijstNr = new ArrayList<String>();
        ArrayList<String> lijstScore = new ArrayList<String>();
        IngelezenFile file = null;
        //String fileContent;
        //Part filePart;
        try {

            //FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Jeroen\\Desktop\\resultaten.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            HSSFSheet worksheet = workbook.getSheet("Blad1");
            HSSFRow row1 = worksheet.getRow(0);

            HSSFCell cel = worksheet.getRow(3).getCell((short) 1);
            cel.setCellType(Cell.CELL_TYPE_STRING);

            String klas = worksheet.getRow(0).getCell((short) 1).getStringCellValue();
            String vak = worksheet.getRow(1).getCell((short) 1).getStringCellValue();
            String test = worksheet.getRow(2).getCell((short) 1).getStringCellValue();
            String totaalPunt = cel.getStringCellValue();

            int i = 6;
            //while (worksheet.getRow(i).getCell((short) 2) == null || worksheet.getRow(i).getCell((short) 2).getCellType() == Cell.CELL_TYPE_BLANK) {
            while (i < 9) {
                HSSFCell scorecel = worksheet.getRow(i).getCell((short) 2);
                scorecel.setCellType(Cell.CELL_TYPE_STRING);
                HSSFCell nrcel = worksheet.getRow(i).getCell((short) 0);
                nrcel.setCellType(Cell.CELL_TYPE_STRING);
                lijstNr.add(worksheet.getRow(i).getCell((short) 0).getStringCellValue());
                lijstScore.add(worksheet.getRow(i).getCell((short) 2).getStringCellValue());
                i++;
            }

            //fileContent = new Scanner(filePart.getInputStream()).useDelimiter("\\A").next();
            //List<IngelezenFile> list = new ArrayList<IngelezenFile>();
            file = new IngelezenFile(klas, vak, test, Integer.parseInt(totaalPunt), lijstNr, lijstScore);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return lijst;

        // upload scores
        /*Klas klas = new Klas();
        klas.setGroep("3TIA1");
        /*em.find(klas.getGroep(), "3TIA1");
        if (u != null) {
            return null;
        }*/

        //Now saving...
        /*
        em.getTransaction().begin();
        em.persist(klas); //em.merge(u); for updates
        em.getTransaction().commit();
        em.close();*/

        //return u;
        /*em.createNativeQuery("INSERT INTO Score (testId, studentId, punt) VALUES(1, 1, ?)")
         .setParameter(1, '1')
         .executeUpdate();*/

        return file;
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
