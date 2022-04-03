package com.ashish.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blocks")
public class Block {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private Long id;
  private Long timeStamp;
  private String data;
  private String previousHash;
  private String hash;
}
