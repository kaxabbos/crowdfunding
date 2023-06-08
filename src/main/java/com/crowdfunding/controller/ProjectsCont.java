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
@RequestMapping("/projects")
public class ProjectsCont extends Attributes {

    @GetMapping("/{id}")
    public String Project(Model model, @PathVariable Long id) {
        AddAttributesProject(model, id);
        return "project";
    }

    @GetMapping("/add")
    public String ProjectAdd(Model model) {
        AddAttributesProjectAdd(model);
        return "project_add";
    }

    @GetMapping("/my")
    public String ProjectsMy(Model model) {
        AddAttributesProjectsMy(model);
        return "my_projects";
    }

    @GetMapping("/waiting/{id}")
    public String ProjectWaiting(@PathVariable Long id) {
        Projects project = projectsRepo.getReferenceById(id);
        project.setStatus(ProjectStatus.WAITING);
        projectsRepo.save(project);
        return "redirect:/projects/my";
    }

    @GetMapping("/edit/{id}")
    public String ProjectEdit(Model model, @PathVariable Long id) {
        AddAttributesProjectEdit(model, id);
        return "project_edit";
    }

    @GetMapping("/delete/{id}")
    public String ProjectDelete(@PathVariable Long id) {
        projectsRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/rent/{id}")
    public String ProjectRent(@PathVariable Long id, @RequestParam int invested) {
        Projects project = projectsRepo.getReferenceById(id);

        project.getStatistics().setInvested(project.getStatistics().getInvested() + invested);
        project.getDescription().setCollected(project.getDescription().getCollected() + invested);
        project.getDescription().setQuantity(project.getDescription().getQuantity() + 1);

        projectsRepo.save(project);

        return "redirect:/projects/{id}";
    }

    @PostMapping("/add")
    public String ProjectAddNew(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam int quantity, @RequestParam Scope scope, @RequestParam String description, @RequestParam Type type, @RequestParam int collected) {
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String[] result_photos;
                String result_screenshot;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_screenshot = "projects/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_screenshot));
                    result_photos[i] = result_screenshot;
                }
                Projects project = new Projects(name, price, result_photos);
                project.setStatistics(new Statistics(project));
                project.setDescription(new ProjectsDescription(scope, type, quantity, description, collected));
                project.setOwner(getUser());
                projectsRepo.save(project);
            } catch (Exception e) {
                AddAttributesProjectAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "project_add";
            }
        } else {
            AddAttributesProjectAdd(model);
            model.addAttribute("message", "Ошибка, некорректный данные!");
            return "project_add";
        }
        return "redirect:/projects/add";
    }

    @PostMapping("/edit/{id}")
    public String ProjectEditOld(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam int quantity, @RequestParam Scope scope, @RequestParam String description, @RequestParam Type type, @RequestParam int collected, @PathVariable Long id) {
        Projects project = projectsRepo.getReferenceById(id);
        String[] result_photos;
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String result_photo;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_photo = "projects/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_photo));
                    result_photos[i] = result_photo;
                }
                project.setPhotos(result_photos);
            } catch (Exception e) {
                AddAttributesProjectAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "project_edit";
            }
        }

        project.setName(name);
        project.setPrice(price);
        projectsRepo.save(project);

        ProjectsDescription projectsDescription = project.getDescription();
        projectsDescription.setDescription(description);
        projectsDescription.setType(type);
        projectsDescription.setScope(scope);
        projectsDescription.setQuantity(quantity);
        projectsDescription.setCollected(collected);
        projectsDescriptionRepo.save(projectsDescription);

        return "redirect:/";
    }
}
