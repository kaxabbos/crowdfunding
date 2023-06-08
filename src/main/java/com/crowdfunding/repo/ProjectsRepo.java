package com.crowdfunding.repo;

import com.crowdfunding.model.Projects;
import com.crowdfunding.model.Users;
import com.crowdfunding.model.enums.ProjectStatus;
import com.crowdfunding.model.enums.Scope;
import com.crowdfunding.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepo extends JpaRepository<Projects, Long> {
    List<Projects> findAllByDescription_ScopeAndDescription_TypeAndStatus(Scope scope, Type type, ProjectStatus status);
    List<Projects> findAllByStatus(ProjectStatus status);
    List<Projects> findAllByOwner(Users owner);
}
