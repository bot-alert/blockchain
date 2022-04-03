package com.ashish.blockchain.service;

import com.ashish.blockchain.model.Block;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BlockService {
  void save(Block block) throws NoSuchAlgorithmException;

  List<Block> getAllBlock();

  Block getLatestBlock();

  void createGenesis(Block block) throws NoSuchAlgorithmException;

  Block getById(long id);

  String isBlockValid() throws NoSuchAlgorithmException;
}
