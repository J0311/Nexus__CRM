package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Integer> {
}
