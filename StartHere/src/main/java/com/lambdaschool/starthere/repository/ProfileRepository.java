package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends PagingAndSortingRepository <Profile, Long> {
}
