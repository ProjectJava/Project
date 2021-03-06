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
 * Klas generated by hbm2java
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Klas.findByGroep", query = "SELECT k FROM Klas k WHERE k.groep = :naam"),
    @NamedQuery(name = "Klas.findById", query = "SELECT k FROM Klas k WHERE k.id = :id"),
})
public class Klas  implements java.io.Serializable {


    @Id
    @GeneratedValue
     private Integer id;
     private String groep;
     @OneToMany
     private List<Test> tests = new ArrayList<Test>(0);
     @OneToMany
     private List<Student> students = new ArrayList<Student>(0);

    public Klas() {
    }

	
    public Klas(String groep) {
        this.groep = groep;
    }
    public Klas(String groep, List<Test> tests, List<Student> students) {
       this.groep = groep;
       this.tests = tests;
       this.students = students;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGroep() {
        return this.groep;
    }
    
    public void setGroep(String groep) {
        this.groep = groep;
    }
    public List<Test> getTests() {
        return this.tests;
    }
    
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    public List<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }




}


