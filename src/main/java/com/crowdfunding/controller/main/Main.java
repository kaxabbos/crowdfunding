package com.crowdfunding.controller.main;

import com.crowdfunding.model.Users;
import com.crowdfunding.repo.ProjectsDescriptionRepo;
import com.crowdfunding.repo.ProjectsRepo;
import com.crowdfunding.repo.StatisticsRepo;
import com.crowdfunding.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Main {

    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected ProjectsRepo projectsRepo;
    @Autowired
    protected ProjectsDescriptionRepo projectsDescriptionRepo;
    @Autowired
    protected StatisticsRepo statisticsRepo;
    @Value("${upload.img}")
    protected String uploadImg;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }
}