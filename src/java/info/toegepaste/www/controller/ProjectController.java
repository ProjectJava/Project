/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.controller;

import info.toegepaste.www.entity.Score;
import info.toegepaste.www.entity.Test;
import info.toegepaste.www.service.ProjectService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Gert-jan
 */
@ManagedBean(name="projectController")
public class ProjectController {
    private List<Score> scores;
    private List<Test> tests;
    
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
    
    
    
    
    @PostConstruct
    public void init() {
        scores = projectService.getAllScores();
        tests = projectService.getAllTests();
    }
    
    
}
