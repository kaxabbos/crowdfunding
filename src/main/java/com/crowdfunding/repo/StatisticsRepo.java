package com.crowdfunding.repo;

import com.crowdfunding.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepo extends JpaRepository<Statistics, Long> {
}
