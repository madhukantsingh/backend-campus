package com.anyaudit.repository;

import com.anyaudit.models.DisclosureGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisclosureGroupsRepository extends JpaRepository<DisclosureGroups,Long> {
}
