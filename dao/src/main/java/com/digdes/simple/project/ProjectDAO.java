package com.digdes.simple.project;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectDAO {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectModel getByCode(String code) {
        try {
            Optional<ProjectModel> optional = projectRepository.getByCode(code);
            if (optional.isPresent()) {
                return optional.get();
            }
            return null;
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
