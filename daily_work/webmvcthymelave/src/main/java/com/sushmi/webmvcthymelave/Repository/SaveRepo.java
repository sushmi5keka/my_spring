package com.sushmi.webmvcthymelave.Repository;


import com.sushmi.webmvcthymelave.Entity.Save;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveRepo extends JpaRepository<Save, Long> {
}
