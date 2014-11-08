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
        ArrayList<String> lijstNaam = new ArrayList<String>();
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
            // deze lus werkt niet als je regels weg doet uit een excel file, dus minder dan 3 studenten kan niet, meer wel!
            while (worksheet.getRow(i) != null && worksheet.getRow(i).getCell((short) 1) != null) {
                HSSFCell scorecel = worksheet.getRow(i).getCell((short) 2);
                scorecel.setCellType(Cell.CELL_TYPE_STRING);
                HSSFCell naamcel = worksheet.getRow(i).getCell((short) 1);
                naamcel.setCellType(Cell.CELL_TYPE_STRING);
                HSSFCell nrcel = worksheet.getRow(i).getCell((short) 0);
                nrcel.setCellType(Cell.CELL_TYPE_STRING);

                lijstNr.add(worksheet.getRow(i).getCell((short) 0).getStringCellValue());
                lijstNaam.add(worksheet.getRow(i).getCell((short) 1).getStringCellValue());
                lijstScore.add(worksheet.getRow(i).getCell((short) 2).getStringCellValue());

                i++;
            }

            file = new IngelezenFile(klas, vak, test, Integer.parseInt(totaalPunt), lijstNr, lijstNaam, lijstScore);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return lijst;

        // upload scores
        insertTest(file);
        return file;
    }
    
    public void insertTest(IngelezenFile excelfile) {
        Query q = em.createNamedQuery("Vak.findByNaam");
        q.setParameter("naam", excelfile.getVak());
        List results = q.getResultList();
        Vak foundVak = null;
        if (!results.isEmpty()) {
            // ignores multiple results
            foundVak = (Vak) results.get(0);
        } else {
            // toevoegen aan database omdat ik stoer ben
            Vak newVak = new Vak();
            newVak.setNaam(excelfile.vak);
            foundVak = newVak;
            em.persist(newVak);
        }

        Query q2 = em.createNamedQuery("Klas.findByGroep");
        q2.setParameter("naam", excelfile.getKlas());
        List results2 = q2.getResultList();
        Klas foundKlas = null;
        if (!results2.isEmpty()) {
            // ignores multiple results
            foundKlas = (Klas) results2.get(0);
        } else {
            // toevoegen aan database omdat ik stoer ben
            Klas newKlas = new Klas();
            newKlas.setGroep(excelfile.klas);
            foundKlas = newKlas;
            em.persist(newKlas);
        }

        int maxId = (Integer) em.createQuery("select max(t.id) from Test t").getSingleResult();

        //test toevoegen
        Test test = new Test();
        test.setId(maxId + 1);
        test.setMaxScore(excelfile.getTotaalScore());
        test.setNaam(excelfile.getTest());
        test.setVak(foundVak);
        test.setKlas(foundKlas);
        em.persist(test);

        // overlopen scores + toevoegen db
        for (int i = 0; i < excelfile.studentenNr.size(); i++) {
            Score score = new Score();
            int maxScoreId = (Integer) em.createQuery("select max(s.id) from Score s").getSingleResult();
            score.setId(maxScoreId + 1);

            Query q3 = em.createNamedQuery("Student.findByNr");
            q3.setParameter("nr", excelfile.studentenNr.get(i));
            List results3 = q3.getResultList();
            Student foundStudent = null;
            if (!results3.isEmpty()) {
                // ignores multiple results
                foundStudent = (Student) results3.get(0);

                // set student hier omdat als de student nog niet zou bestaan anders error
                score.setStudent(foundStudent);
            } else {
                // toevoegen aan database omdat ik stoer ben
                Student newStudent = new Student();

                newStudent.setStudentnummer(excelfile.studentenNr.get(i));
                newStudent.setNaam(excelfile.studentenNaam.get(i));
                newStudent.setKlas(foundKlas);
                newStudent.setEmailadres(excelfile.studentenNr.get(i) + "@student.be");
                em.persist(newStudent);

                // set student hier omdat als de student nog niet zou bestaan anders error
                score.setStudent(newStudent);
            }

            score.setPunt(Double.parseDouble(excelfile.studentenScore.get(i)));
            score.setTest(test);

            em.persist(score);
        }
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
