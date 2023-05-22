package com.wte.be.wte.repository;

import com.wte.be.wte.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface SearchLogEntityRepository extends JpaRepository<SearchLogEntity, String>{

}
