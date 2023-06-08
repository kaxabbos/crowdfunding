package com.crowdfunding.repo;

import com.crowdfunding.model.ProjectsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsDescriptionRepo extends JpaRepository<ProjectsDescription, Long> {
}
