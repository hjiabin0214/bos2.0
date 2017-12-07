package cn.hjiabin.bos.domain.system;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * @description:��̨�û�
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private int id; // ����
	@Column(name = "C_BIRTHDAY")
	private Date birthday; // ����
	@Column(name = "C_GENDER")
	private String gender; // �Ա�
	@Column(name = "C_PASSWORD")
	private String password; // ����
	@Column(name = "C_REMARK")
	private String remark; // ��ע
	@Column(name = "C_STATION")
	private String station; // ״̬
	@Column(name = "C_TELEPHONE")
	private String telephone; // ��ϵ�绰
	@Column(name = "C_USERNAME", unique = true)
	private String username; // ��½�û���
	@Column(name = "C_NICKNAME")
	private String nickname; // ��ʵ����

	@ManyToMany
	@JoinTable(name = "T_USER_ROLE", joinColumns = {
			@JoinColumn(name = "C_USER_ID", referencedColumnName = "C_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "C_ROLE_ID", referencedColumnName = "C_ID") })
	private Set<Role> roles = new HashSet<Role>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@JSON(serialize=false)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
