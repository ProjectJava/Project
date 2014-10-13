/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.service;

import info.toegepaste.www.entity.Score;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gert-jan
 */
@Stateless
public class ProjectServiceImpl implements ProjectService {
    @PersistenceContext
    private EntityManager em;
        
    @Override
    public String test() {
        return "test";
    }
    
    @Override
    public List<Score> getAllScores() {
        Query q = em.createQuery("SELECT s FROM Score s");
        
        return (List<Score>) q.getResultList();
    }
}
