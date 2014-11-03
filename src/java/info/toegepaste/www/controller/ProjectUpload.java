/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.toegepaste.www.controller;

import info.toegepaste.www.entity.IngelezenFile;
import info.toegepaste.www.entity.Score;
import info.toegepaste.www.service.ProjectService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
@ManagedBean(name = "projectUpload")
public class ProjectUpload {

    @EJB
    private ProjectService projectService;

    private Part file;
    private String fileContent;
    private InputStream fs;
    private IngelezenFile exceldinges;

    public String upload() throws IOException {
        fs = file.getInputStream();
        exceldinges = projectService.getExcelScores(fs);
        return "excelresult"; // zotte shit redirect naar resultate.xhtml (c) Jeroen 1
    }

    public IngelezenFile getIngelezenFile() {
        return exceldinges;
    }
    
    public Part getFile(){return file;}
    public void setFile(Part file) {this.file = file;}

}
