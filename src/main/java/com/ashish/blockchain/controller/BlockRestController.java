package com.ashish.blockchain.controller;

import com.ashish.blockchain.constant.ResponseMessageConstant;
import com.ashish.blockchain.model.Block;
import com.ashish.blockchain.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/block")
@RequiredArgsConstructor
public class BlockRestController {
  private final BlockService blockService;

  @PostMapping
  public ResponseEntity<String> saveBlock(@RequestBody Block block) throws NoSuchAlgorithmException {
    blockService.save(block);
    return ResponseEntity.ok(ResponseMessageConstant.BLOCK_SAVED);
  }

  @GetMapping
  public ResponseEntity<List<Block>> getListOfBlock() {
    return ResponseEntity.ok(blockService.getAllBlock());
  }

  @GetMapping("/latest")
  public ResponseEntity<Block> getLatestBlock() {
    return ResponseEntity.ok(blockService.getLatestBlock());
  }

  @PostMapping("/create-genesis")
  public ResponseEntity<String> createGenesis(@RequestBody Block block) throws NoSuchAlgorithmException {
    blockService.createGenesis(block);
    return ResponseEntity.ok(ResponseMessageConstant.GENESIS_CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Block> getByIndex(@PathVariable long id) {
    return ResponseEntity.ok(blockService.getById(id));
  }

  @GetMapping("/is-valid")
  public ResponseEntity<String> isValidBlock() throws NoSuchAlgorithmException {
    return ResponseEntity.ok(blockService.isBlockValid());
  }
}
