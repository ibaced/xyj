package com.xyjhw.xyj.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "action_id")
    private long action_id;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "name")
    private String name;

    @Column(name = "idcard")
    private String idcard;

    @Column(name = "beizhu")
    private String beizhu;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAction_id() {
        return action_id;
    }

    public void setAction_id(long action_id) {
        this.action_id = action_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String toString(){
        return "Action [id=" + id + ", action_id=" + action_id + ", telephone=" + telephone +", name=" + name +", idcard=" + idcard +", beizhu=" + beizhu +"]";
    }
}
