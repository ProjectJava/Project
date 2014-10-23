/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.controller;

import info.toegepaste.www.entity.Klas;
import info.toegepaste.www.entity.Score;
import info.toegepaste.www.entity.Student;
import info.toegepaste.www.entity.Test;
import info.toegepaste.www.entity.Vak;
import info.toegepaste.www.service.ProjectService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Gert-jan
 */
@ManagedBean(name = "projectController")
public class ProjectController {

    private List<Score> scores;
    private List<Test> tests;
    private List<Klas> klassen;
    private List<Vak> vakken;
    private List<Student> studenten;
    private int selectedTestId, selectedStudentId, selectedKlasId, selectedVakId;

    @EJB
    private ProjectService projectService;

    public String getTest() {
        return projectService.test();
    }

    public void setTest(ProjectService hs) {
        this.projectService = hs;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Klas> getKlassen() {
        return klassen;
    }

    public void setKlassen(List<Klas> klassen) {
        this.klassen = klassen;
    }

    public List<Vak> getVakken() {
        return vakken;
    }

    public void setVakken(List<Vak> vakken) {
        this.vakken = vakken;
    }

    public List<Student> getStudenten() {
        return studenten;
    }

    public void setStudenten(List<Student> studenten) {
        this.studenten = studenten;
    }
    
    

    public int getSelectedTestId() {
        return selectedTestId;
    }

    public void setSelectedTestId(int selectedTestId) {
        this.selectedTestId = selectedTestId;
    }

    public int getSelectedStudentId() {
        return selectedStudentId;
    }

    public void setSelectedStudentId(int selectedStudentId) {
        this.selectedStudentId = selectedStudentId;
    }

    public int getSelectedKlasId() {
        return selectedKlasId;
    }

    public void setSelectedKlasId(int selectedKlasId) {
        this.selectedKlasId = selectedKlasId;
    }

    public int getSelectedVakId() {
        return selectedVakId;
    }

    public void setSelectedVakId(int selectedVakId) {
        this.selectedVakId = selectedVakId;
    }

    

    public void getScoresByTest() {
        scores = projectService.getAllScoresByTest(selectedTestId);
    }
    
    public void getScoresByVak() {
        scores = projectService.getAllScoresByVak(selectedVakId);
    }
    
    public void getScoresByKlas() {
        scores = projectService.getAllScoresByKlas(selectedKlasId);
    }
    
    public void getScoresByStudent() {
        scores = projectService.getAllScoresByStudent(selectedStudentId);
    }
    @PostConstruct
    public void init() {

        scores = projectService.getAllScores();

        tests = projectService.getAllTests();
        
        vakken = projectService.getAllVakken();
        
        klassen = projectService.getAllKlassen();
        
        studenten = projectService.getAllStudenten();
    }

}
