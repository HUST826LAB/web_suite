package com.cotech.model;

import java.io.Serializable;

public class TRes implements Serializable {
  private Long res_id;
  private Long user_id;
  private String cookie_id;
  private String coordinate;
  private String ip;
  private String device;
  private Integer sex;
  private Integer constellation;
  private Long blood;
  private String address;
  private Long score;
  private Long gold;
  private String group;
  private String create_time;
  private Long referee;
  private String deviation;
  private Long time_len;
  private String location_id;

  public Long getRes_id() {
    return res_id;
  }

  public void setRes_id(Long res_id) {
    this.res_id = res_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getCookie_id() {
    return cookie_id;
  }

  public void setCookie_id(String cookie_id) {
    this.cookie_id = cookie_id;
  }

  public String getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public Integer getConstellation() {
    return constellation;
  }

  public void setConstellation(Integer constellation) {
    this.constellation = constellation;
  }

  public Long getBlood() {
    return blood;
  }

  public void setBlood(Long blood) {
    this.blood = blood;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Long getGold() {
    return gold;
  }

  public void setGold(Long gold) {
    this.gold = gold;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getCreate_time() {
    return create_time;
  }

  public void setCreate_time(String create_time) {
    this.create_time = create_time;
  }

  public Long getReferee() {
    return referee;
  }

  public void setReferee(Long referee) {
    this.referee = referee;
  }

  public String getDeviation() {
    return deviation;
  }

  public void setDeviation(String deviation) {
    this.deviation = deviation;
  }

  public Long getTime_len() {
    return time_len;
  }

  public void setTime_len(Long time_len) {
    this.time_len = time_len;
  }

  public String getLocation_id() {
    return location_id;
  }

  public void setLocation_id(String location_id) {
    this.location_id = location_id;
  }

  @Override
  public String toString(){
    return String.format("入参为:[res_id=%d,user_id=%d,coordinate=%s,ip=%s,device=%s,sex=%d,constellation=%d,blood=%d,address=%s,score=%d,gold=%d,group=%s,create_time=%s,referee=%d,deviation=%s,time_len=%d,location_id=%s]", res_id, user_id, coordinate, ip, device, sex, constellation, blood, address, score, gold, group, create_time, referee, deviation, time_len, location_id);
  }
}
