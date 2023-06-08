package com.crowdfunding.controller;

import com.crowdfunding.controller.main.Attributes;
import com.crowdfunding.model.Projects;
import com.crowdfunding.model.ProjectsDescription;
import com.crowdfunding.model.Statistics;
import com.crowdfunding.model.enums.ProjectStatus;
import com.crowdfunding.model.enums.Scope;
import com.crowdfunding.model.enums.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/apps")
public class AppsCont extends Attributes {

    @GetMapping
    public String Project(Model model) {
        AddAttributesApps(model);
        return "apps";
    }

    @GetMapping("/active/{id}")
    public String ProjectActive(@PathVariable Long id) {
        Projects project = projectsRepo.getReferenceById(id);
        project.setStatus(ProjectStatus.ACTIVE);
        projectsRepo.save(project);
        return "redirect:/apps";
    }

    @GetMapping("/reject/{id}")
    public String ProjectReject(@PathVariable Long id) {
        Projects project = projectsRepo.getReferenceById(id);
        project.setStatus(ProjectStatus.REJECT);
        projectsRepo.save(project);
        return "redirect:/apps";
    }
}
