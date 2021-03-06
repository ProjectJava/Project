/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.service;

import info.toegepaste.www.entity.IngelezenFile;
import info.toegepaste.www.entity.Klas;
import info.toegepaste.www.entity.Score;
import info.toegepaste.www.entity.Student;
import java.util.ArrayList;
import info.toegepaste.www.entity.Test;
import info.toegepaste.www.entity.Vak;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Gert-jan
 */
public interface ProjectService {
    public String test();
    public IngelezenFile getExcelScores(InputStream fs);
    public List<Score> getAllScores();
    public List<Score> getAllScoresByTest(int id);
    public List<Score> getAllScoresByVak(int id);
    public List<Score> getAllScoresByKlas(int id);
    public List<Score> getAllScoresByStudent(int id);
    public List<Test> getAllTests();
    public List<Vak> getAllVakken();
    public List<Student> getAllStudenten();
    public List<Klas> getAllKlassen();
    
    public void createPDF(List<Score> scores);
    public void extractDocLevelAttachments(String filename) throws IOException;
    public void createPDFje(List<Score> scores);
    
    // ajax 
    public Klas getKlasById(int klasId);
    public List<Vak> getVakkenByKlas(Klas klas);
    public Vak getVakById(int vakId);
    public List<Test> getTestenByVak(Vak vak, Klas klas);
    public Test getTestById(int testId);
    public List<Score> getScoresByTest(Test test);
    
    public Student getStudentById(int studentId);
    public List<Score> getScoresByStudent(Student student);
}
