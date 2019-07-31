package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository <Profile, Long> {
}
