
package com.example.RuleEngineApp.Repository;

import com.example.RuleEngineApp.Model.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
}