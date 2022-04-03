package com.ashish.blockchain.service.impl;

import com.ashish.blockchain.constant.ResponseMessageConstant;
import com.ashish.blockchain.model.Block;
import com.ashish.blockchain.repository.BlockRepository;
import com.ashish.blockchain.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {
  private final BlockRepository blockRepository;

  @Override
  public void save(Block block) throws NoSuchAlgorithmException {
    Block blockFromDb = getLatestBlock();
    block.setId(blockFromDb.getId() + 1);
    block.setPreviousHash(blockFromDb.getHash());
    block.setTimeStamp(new Date().getTime());
    block.setHash(calculateHash(block));
    blockRepository.save(block);
  }

  @Override
  public List<Block> getAllBlock() {
    return blockRepository.findAll();
  }

  @Override
  public Block getLatestBlock() {
    return blockRepository.findTop1ByOrderByIdDesc();
  }

  @Override
  public void createGenesis(Block block) throws NoSuchAlgorithmException {
    block.setId(1L);
    block.setTimeStamp(new Date().getTime());
    block.setHash(calculateHash(block));
    block.setPreviousHash("first-block-hash");
    blockRepository.save(block);
  }

  @Override
  public Block getById(long id) {
    return blockRepository.findById(id).orElse(new Block());
  }

  @Override
  public String isBlockValid() throws NoSuchAlgorithmException {
    List<Block> blockList = blockRepository.findAll();
    for (int i = 1; i < blockList.size(); i++) {
      Block currentBlock = blockList.get(i);
      Block previousBlock = blockList.get(i - 1);
      if (!currentBlock.getHash().equals(calculateHash(currentBlock))) {
        return ResponseMessageConstant.NOT_A_VALID_BLOCK;
      }
      if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
        return ResponseMessageConstant.NOT_A_VALID_BLOCK;
      }
    }
    return ResponseMessageConstant.VALID_BLOCK;
  }

  private String calculateHash(Block block) throws NoSuchAlgorithmException {
    String text = String.valueOf(block.getData()) + block.getTimeStamp() + block.getPreviousHash() + block.getId();
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    final byte[] bytes = messageDigest.digest(text.getBytes());
    final StringBuilder hexString = new StringBuilder();
    for (final byte b : bytes) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append("0");
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
