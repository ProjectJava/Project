/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.service;

import info.toegepaste.www.entity.Klas;
import info.toegepaste.www.entity.Score;
import info.toegepaste.www.entity.Student;
import java.util.ArrayList;
import info.toegepaste.www.entity.Test;
import info.toegepaste.www.entity.Vak;
import java.util.List;

/**
 *
 * @author Gert-jan
 */
public interface ProjectService {
    public String test();
    public ArrayList<String> getExcel();
    public List<Score> getAllScores();
    public List<Score> getAllScoresByTest(int id);
    public List<Score> getAllScoresByVak(int id);
    public List<Score> getAllScoresByKlas(int id);
    public List<Score> getAllScoresByStudent(int id);
    public List<Test> getAllTests();
    public List<Vak> getAllVakken();
    public List<Student> getAllStudenten();
    public List<Klas> getAllKlassen();
}
