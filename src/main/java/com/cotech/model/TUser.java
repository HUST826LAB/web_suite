package com.cotech.model;

import java.io.Serializable;

public class TUser implements Serializable {

    private Long user_id;
    private String username;
    private String password;
    private String uname;
    private String ip;
    private String device;
    private Integer sex;
    private Integer star;
    private Integer constellation;
    private Integer blood;
    private String address;
    private Long score;
    private Long gold;
    private Long group;
    private Integer star_a;
    private Integer star_b;
    private Integer star_c;
    private Integer star_d;
    private Integer star_e;
    private Integer star_f;
    private String create_time;
    private String approve_time;
    private Integer status;
    private Long approver;
    private String location_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getConstellation() {
        return constellation;
    }

    public void setConstellation(Integer constellation) {
        this.constellation = constellation;
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

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Integer getStar_a() {
        return star_a;
    }

    public void setStar_a(Integer star_a) {
        this.star_a = star_a;
    }

    public Integer getStar_b() {
        return star_b;
    }

    public void setStar_b(Integer star_b) {
        this.star_b = star_b;
    }

    public Integer getStar_c() {
        return star_c;
    }

    public void setStar_c(Integer star_c) {
        this.star_c = star_c;
    }

    public Integer getStar_e() {
        return star_e;
    }

    public void setStar_e(Integer star_e) {
        this.star_e = star_e;
    }

    public Integer getStar_f() {
        return star_f;
    }

    public void setStar_f(Integer star_f) {
        this.star_f = star_f;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getApprove_time() {
        return approve_time;
    }

    public void setApprove_time(String approve_time) {
        this.approve_time = approve_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getApprover() {
        return approver;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passoword) {
        this.password = passoword;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public Integer getStar_d() {
        return star_d;
    }

    public void setStar_d(Integer star_d) {
        this.star_d = star_d;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    @Override
    public String toString(){
        return String.format("入参为:[user_id=%d,username=%s,password=%s,uname=%s,ip=%s,device=%s,sex=%d,constellation=%d,blood=%d,address=%s,score=%d,gold=%d,group=%s,star_a=%d,star_b=%d,star_c=%d,star_d=%d,star_e=%d,star_f=%d,create_time=%s,approve_time=%s,status=%d,approver=%d]", user_id, username, password, uname, ip, device, sex, constellation, blood, address, score, gold, group, star_a, star_b, star_c, star_d, star_e, star_f, create_time, approve_time, status, approver);
    }

}
