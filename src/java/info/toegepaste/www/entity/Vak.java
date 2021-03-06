package info.toegepaste.www.entity;
// Generated 13-okt-2014 10:31:43 by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Vak generated by hbm2java
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Vak.findByNaam", query = "SELECT v FROM Vak v WHERE v.naam = :naam"),
    @NamedQuery(name = "Vak.findById", query = "SELECT v FROM Vak v WHERE v.id = :id"),
        @NamedQuery(name = "Vak.findByKlas", query = "SELECT DISTINCT v FROM Vak v INNER JOIN Test t ON t.vak = v WHERE t.klas = :klas")                                                      
})
public class Vak  implements java.io.Serializable {

    @Id
    @GeneratedValue
     private Integer id;
     private String naam;
     @OneToMany
     private List<Test> tests = new ArrayList<Test>(0);

    public Vak() {
    }

	
    public Vak(String naam) {
        this.naam = naam;
    }
    public Vak(String naam, List<Test> tests) {
       this.naam = naam;
       this.tests = tests;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNaam() {
        return this.naam;
    }
    
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public List<Test> getTests() {
        return this.tests;
    }
    
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }




}


