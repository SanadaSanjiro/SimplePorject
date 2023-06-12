package com.digdes.simple.dao.project;

import com.digdes.simple.dao.employee.EmployeeSpecification;
import com.digdes.simple.dto.project.ProjectSrchDTO;
import com.digdes.simple.model.project.ProjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectDAO {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectModel getByCode(String code) {
        try {
            return projectRepository.getByCode(code).get();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public ProjectModel create(ProjectModel projectModel) {
        try {
            return projectRepository.save(projectModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        System.out.println("ProjectDAO возвращает null");
        return null;
    }

    public ProjectModel update (ProjectModel projectModel) {
        try {
            return projectRepository.save(projectModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public ProjectModel changeStatus (ProjectModel projectModel) {
        try {
            return projectRepository.save(projectModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public List<ProjectModel> getFiltered(ProjectSrchDTO dto) {
        try {
            return projectRepository.findAll(ProjectSpecification.getFilters(dto));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public List<ProjectModel> getAll() {
        List<ProjectModel> list = new ArrayList<>();
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }
}
