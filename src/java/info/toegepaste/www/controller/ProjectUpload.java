/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.controller;

import info.toegepaste.www.entity.Score;
import info.toegepaste.www.service.ProjectService;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;
//import javax.mail.Part;
/**
 *
 * @author jeroen
 */
@ManagedBean(name="projectUpload")
public class ProjectUpload {
    
    @EJB
    private ProjectService projectService;
    
    private Part file;
    private String fileContent;
    
    public void upload() {
        try {
            fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
        } catch (IOException e) {
            //error
            e.printStackTrace();
        }       
        
}
    public Part getFile()
        {
            return file;           
        }
        
        public void setFile(Part file) {
            this.file = file;
        }
}
