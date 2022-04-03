package com.ashish.blockchain.repository;

import com.ashish.blockchain.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
  Block findTop1ByOrderByIdDesc();
}
