package com.tkm.sample.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MngAnmRepository extends JpaRepository<MngAnmData, Integer> {

}
