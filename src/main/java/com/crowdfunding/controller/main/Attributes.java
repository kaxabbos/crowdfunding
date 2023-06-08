package com.crowdfunding.controller.main;

import com.crowdfunding.model.Statistics;
import com.crowdfunding.model.enums.ProjectStatus;
import com.crowdfunding.model.enums.Scope;
import com.crowdfunding.model.enums.Type;
import com.crowdfunding.model.enums.Role;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("scopes", Scope.values());
        model.addAttribute("types", Type.values());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesProject(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("project", projectsRepo.getReferenceById(id));
    }


    protected void AddAttributesProjectAdd(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesProjectEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("project", projectsRepo.getReferenceById(id));
    }

    protected void AddAttributesCatalog(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("projects", projectsRepo.findAllByStatus(ProjectStatus.ACTIVE));
    }

    protected void AddAttributesApps(Model model) {
        AddAttributes(model);
        model.addAttribute("projects", projectsRepo.findAllByStatus(ProjectStatus.WAITING));
    }

    protected void AddAttributesProjectsMy(Model model) {
        AddAttributes(model);
        model.addAttribute("projects",projectsRepo.findAllByOwner(getUser()));
    }

    protected void AddAttributesCatalogSearch(Model model, Scope scope, Type type) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("bSelect", scope);
        model.addAttribute("fSelect", type);
        model.addAttribute("projects", projectsRepo.findAllByDescription_ScopeAndDescription_TypeAndStatus(scope, type, ProjectStatus.ACTIVE));
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        List<Statistics> stats = statisticsRepo.findAll();
        stats.sort(Comparator.comparing(Statistics::getInvested));
        Collections.reverse(stats);
        int[] invests = new int[5];
        String[] investsName = new String[5];

        for (int i = 0; i < stats.size(); i++) {
            if (i == 5) break;
            invests[i] = stats.get(i).getInvested();
            investsName[i] = stats.get(i).getProject().getName();
        }

        model.addAttribute("stats", stats);
        model.addAttribute("invests", invests);
        model.addAttribute("investsName", investsName);
    }
}
