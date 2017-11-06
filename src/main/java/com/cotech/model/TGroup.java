package com.cotech.model;

import java.io.Serializable;

public class TGroup implements Serializable {
  private String group_id;
  private Long numSum;
  private String name;
  private Long score;
  private Long owner;
  private Long creator;
  private String create_time;

  public String getGroup_id() {
    return group_id;
  }

  public void setGroup_id(String group_id) {
    this.group_id = group_id;
  }

  public Long getNumSum() {
    return numSum;
  }

  public void setNumSum(Long numSum) {
    this.numSum = numSum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Long getOwner() {
    return owner;
  }

  public void setOwner(Long owner) {
    this.owner = owner;
  }

  public Long getCreator() {
    return creator;
  }

  public void setCreator(Long creator) {
    this.creator = creator;
  }

  public String getCreate_time() {
    return create_time;
  }

  public void setCreate_time(String create_time) {
    this.create_time = create_time;
  }

  @Override
  public String toString(){
      return String.format("入参为:[group_id=%s,name=%s,score=%s,create_time=%s,owner=%d,creator=%d]", group_id, name, score, create_time, owner, creator);
  }
}
