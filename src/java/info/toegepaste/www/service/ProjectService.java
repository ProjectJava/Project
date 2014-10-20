/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.service;

import info.toegepaste.www.entity.Score;
import info.toegepaste.www.entity.Test;
import java.util.List;

/**
 *
 * @author Gert-jan
 */
public interface ProjectService {
    public String test();
    public List<Score> getAllScores();
    public List<Test> getAllTests();
}
