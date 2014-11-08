/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author jeroen
 */
//@Entity
public class IngelezenFile {

    public String klas, vak, test;
    public int totaalScore;
    public List<String> studentenNr = new ArrayList<String>();    
    public List<String> studentenScore = new ArrayList<String>();   
    public List<String> studentenNaam = new ArrayList<String>();

    public IngelezenFile(String klas, String vak, String test, int totaalScore, List<String>studentenNr, List<String>studentenNaam, List<String>studentenScore) {
        this.klas = klas;
        this.vak = vak;
        this.test = test;
        this.totaalScore = totaalScore;
        this.studentenNr = studentenNr;
        this.studentenScore = studentenScore;
        this.studentenNaam = studentenNaam;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public String getVak() {
        return vak;
    }

    public void setVak(String vak) {
        this.vak = vak;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getTotaalScore() {
        return totaalScore;
    }

    public void setTotaalScore(int totaalScore) {
        this.totaalScore = totaalScore;
    }

    public List<String> getStudentenNr() {
        return studentenNr;
    }

    public void setStudentenNr(List<String> studentenNr) {
        this.studentenNr = studentenNr;
    }
    
    public List<String> getStudentenScore() {
        return studentenScore;
    }

    public void setStudentenScore(List<String> studentenScore) {
        this.studentenScore = studentenScore;
    }
    
     public List<String> getStudentenNaam() {
        return studentenNaam;
    }

    public void setStudentenNaam(List<String> studentenNaam) {
        this.studentenNaam = studentenNaam;
    }

}