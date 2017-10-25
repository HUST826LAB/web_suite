package com.cotech.model;

public class TRes {
  private Long res_id;
  private Long user_id;
  private String coordinate;
  private String ip;
  private String device;
  private Long sex;
  private Long constellation;
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

  public Long getSex() {
    return sex;
  }

  public void setSex(Long sex) {
    this.sex = sex;
  }

  public Long getConstellation() {
    return constellation;
  }

  public void setConstellation(Long constellation) {
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
    StringBuilder stringBuilder = new StringBuilder();
    return stringBuilder.append("入参为:[res_id=").append(res_id+",")
            .append("user_id=").append(user_id+",")
            .append("coordinate=").append(coordinate+",")
            .append("ip=").append(ip+",")
            .append("device=").append(device+",")
            .append("sex=").append(sex+",")
            .append("constellation=").append(constellation+",")
            .append("blood=").append(blood+",")
            .append("address=").append(address+",")
            .append("score=").append(score+",")
            .append("gold=").append(gold+",")
            .append("group=").append(group+",")
            .append("create_time=").append(create_time+",")
            .append("referee=").append(referee+",")
            .append("deviation=").append(deviation+",")
            .append("time_len=").append(time_len+",")
            .append("location_id=").append(location_id+"]").toString();
  }
}
