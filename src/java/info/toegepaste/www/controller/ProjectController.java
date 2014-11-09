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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Gert-jan
 */
@ManagedBean(name = "projectController")
public class ProjectController {

    @EJB
    private ProjectService projectService;
    
    // voor ajax
    private int gekozenKlas, gekozenVak, gekozenTest, studentID;

    private List<Score> scores;
    private List<Test> tests;
    private List<Klas> klassen;
    private List<Vak> vakken;
    private List<Student> studenten;
    private int selectedTestId, selectedStudentId, selectedKlasId, selectedVakId;

    // init van checkboxstuff
    private Map<Long, Boolean> selectedScoreIds = new HashMap<Long, Boolean>();
    private List<Score> selectedDataList;
    
    private List<Score> resultatenStudent;
    private double algemeenTotaalMax;
    private double algemeenTotaal;
    
    private ArrayList<Vak> behaaldeScoresVak = new ArrayList<Vak>();
    private ArrayList<Double> behaaldeScoresArray = new ArrayList<Double>();
    private ArrayList<Double> maximumScoresArray = new ArrayList<Double>();

    public static final String FILENAME = "resultaat.pdf";
    public static final String PATH = "";
    public static final String RESULT = String.format(PATH, FILENAME);

    // Actions van checkbox stuff
    public void getSelectedItems() throws FileNotFoundException, IOException {

        // Get selected items.
        selectedDataList = new ArrayList<Score>();
        for (Score dataItem : scores) {
            if (selectedScoreIds.get(dataItem.getId()).booleanValue()) {
                selectedDataList.add(dataItem);
                selectedScoreIds.remove(dataItem.getId()); // Reset.
            }
        }

        // Do your thing with the MyData items in List selectedDataList.
        // MAAK HIER DE PDF AAN
        // IN DE ARRAY SELECTEDDATALIST ZITTEN ALLE SCORES DIE GESELECTEERD ZIJN ALS SCORE OBJECT
        projectService.createPDFje(selectedDataList);

        //return "resultatenTest"; // Navigation case. // Ga naar de pdf / ga naar een bedankt pagina / doe iets nuttig
    }

    // Getters -----------------------------------------------------------------------------------
    public Map<Long, Boolean> getSelectedIds() {
        return selectedScoreIds;
    }

    public List<Score> getSelectedDataList() {
        return selectedDataList;
    }

    // einde checkbox stuff
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
        if (selectedTestId != 0) {
            scores = projectService.getAllScoresByTest(selectedTestId);
        } else {
            scores = projectService.getAllScores();
        }
    }

    public void getScoresByVak() {
        if (selectedVakId != 0) {
            scores = projectService.getAllScoresByVak(selectedVakId);
        } else {
            scores = projectService.getAllScores();
        }
    }

    public void getScoresByKlas() {
        if (selectedKlasId != 0) {
            scores = projectService.getAllScoresByKlas(selectedKlasId);
        } else {
            scores = projectService.getAllScores();
        }
    }

    public void getScoresByStudent() {
        if (selectedStudentId != 0) {
            scores = projectService.getAllScoresByStudent(selectedStudentId);
        } else {
            scores = projectService.getAllScores();
        }
    }
    
    // buh    
    public ArrayList<Vak> getBehaaldeScoresVak() {
        return behaaldeScoresVak;
    }

    public void setBehaaldeScoresVak(ArrayList<Vak> behaaldeScoresVak) {
        this.behaaldeScoresVak = behaaldeScoresVak;
    }

    public ArrayList<Double> getBehaaldeScoresArray() {
        return behaaldeScoresArray;
    }

    public void setBehaaldeScoresArray(ArrayList<Double> behaaldeScoresArray) {
        this.behaaldeScoresArray = behaaldeScoresArray;
    }

    public ArrayList<Double> getMaximumScoresArray() {
        return maximumScoresArray;
    }

    public void setMaximumScoresArray(ArrayList<Double> maximumScoresArray) {
        this.maximumScoresArray = maximumScoresArray;
    }
    //buh
    
    public void scoresOphalenStudent(){
        Student student = projectService.getStudentById(studentID);
        scores = projectService.getScoresByStudent(student);
        
        int index = 0; 
        for(Score score : scores)
            {
                // array ipv hash
                if (behaaldeScoresVak.contains(score.getTest().getVak())) {
                    // vak zit er al in
                } else
                {
                    behaaldeScoresVak.add(score.getTest().getVak());
                }
                
                index = behaaldeScoresVak.indexOf(score.getTest().getVak());
                if (behaaldeScoresArray.size() < behaaldeScoresVak.size()) {
                    behaaldeScoresArray.add(score.getPunt());
                } else if (behaaldeScoresArray.get(index) != null) {
                    // is al een score voor het vak
                    Double vorige = behaaldeScoresArray.get(index);
                    behaaldeScoresArray.set(index, vorige + score.getPunt());
                }
                if (maximumScoresArray.size() < behaaldeScoresVak.size()) {
                    maximumScoresArray.add(score.getTest().getMaxScore());
                } else if (behaaldeScoresArray.get(index) != null) {
                    // is al max voor dit vak
                    Double vorige = maximumScoresArray.get(index);
                    maximumScoresArray.set(index, vorige + score.getTest().getMaxScore());
                }                
                
                /*Double previousValue = behaaldeScores.get(score.getTest().getVak());
                if(previousValue == null){ 
                    previousValue = 0.0;
                }
                behaaldeScores.put(score.getTest().getVak(), previousValue + score.getPunt());
                
                Double previousValue2 = maximumScores.get(score.getTest().getVak());
                if(previousValue2 == null){ 
                    previousValue2 = 0.0;
                }  
                maximumScores.put(score.getTest().getVak(), previousValue2 + score.getTest().getMaxScore());*/
                
                algemeenTotaal+= score.getPunt();
                
                algemeenTotaalMax+= score.getTest().getMaxScore();
                //System.out.println("woops" + score.getTest().getVak().getNaam() + (previousValue + score.getPunt()) );
            }

        
    }
    
    // ajax scores dropdown invullen
     public void vakkenOphalen(){        
        Klas klas = projectService.getKlasById(gekozenKlas);       
        vakken = projectService.getVakkenByKlas(klas);
    }
    
    public void testenOphalen(){
        Vak vak = projectService.getVakById(gekozenVak);
        // klas is hier ook nodig, anders krijg je alle testen van een bepaald vak
        System.out.println("Vaknaam: " + vak.getNaam() + " + KlasId = " + gekozenKlas + ", functie: " + getGekozenKlas());
        // Als ik hier gekozenKlas variable wil gebruiken is die altijd 0. Weet niet hoe op te lossen!
        Klas klas = projectService.getKlasById(2251);         
        tests = projectService.getTestenByVak(vak, klas);
    }
    
    public void scoresOphalen(){
        Test test = projectService.getTestById(gekozenTest);
        scores = projectService.getScoresByTest(test);
    }
    
    public int getGekozenKlas() {
        return gekozenKlas;
    }

    public void setGekozenKlas(int gekozenKlas) {
        this.gekozenKlas = gekozenKlas;
    }
    
    public int getGekozenVak() {
        return gekozenVak;
    }

    public void setGekozenVak(int gekozenVak) {
        this.gekozenVak = gekozenVak;
    }
    
    public int getGekozenTest() {
        return gekozenTest;
    }

    public void setGekozenTest(int gekozenTest) {
        this.gekozenTest = gekozenTest;
    }
    
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    public List<Score> getResultatenStudent() {
        return resultatenStudent;
    }

    public void setResultatenStudent(List<Score> resultatenStudent) {
        this.resultatenStudent = resultatenStudent;
    }
    
    
    public double getAlgemeenTotaalMax() {
        return algemeenTotaalMax;
    }

    public void setAlgemeenTotaalMax(double algemeenTotaalMax) {
        this.algemeenTotaalMax = algemeenTotaalMax;
    }
    
    public double getAlgemeenTotaal() {
        return algemeenTotaal;
    }

    public void setAlgemeenTotaal(double algemeenTotaal) {
        this.algemeenTotaal = algemeenTotaal;
    }
    
    // einde ajax stuff

    @PostConstruct
    public void init() {

        scores = projectService.getAllScores();

        tests = projectService.getAllTests();

        vakken = projectService.getAllVakken();

        klassen = projectService.getAllKlassen();

        studenten = projectService.getAllStudenten();
    }

}
