package cn.hjiabin.bos.domain.take_delivery;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import cn.hjiabin.bos.constans.Constants;


/**
 * @description:������Ϣʵ����
 */
@Entity
@Table(name = "T_PROMOTION")
@XmlRootElement(name = "promotion")
public class Promotion implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id;
	@Column(name = "C_TITLE")
	private String title; // ������Ҫ(����)
	@Column(name = "C_TITLE_IMG")
	private String titleImg; // ����ͼƬ
	@Column(name = "C_ACTIVE_SCOPE")
	private String activeScope;// ���Χ
	@Column(name = "C_START_DATE")
	private Date startDate; // ����ʱ��
	@Column(name = "C_END_DATE")
	private Date endDate; // ʧЧʱ��
	@Column(name = "C_UPDATE_TIME")
	private Date updateTime; // ����ʱ��
	@Column(name = "C_UPDATE_UNIT")
	private String updateUnit; // ���µ�λ
	@Column(name = "C_UPDATE_USER")
	private String updateUser;// ������ �������̨�û�����
	@Column(name = "C_STATUS")
	private String status = "1"; // ״̬ ��ȡֵ��1.������ 2. �ѽ���
	@Column(name = "C_DESCRIPTION")
	private String description; // ��������(�������Ϣ)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleImg() {
		if (titleImg.startsWith(Constants.BOS_MANAGEMENT_URL)) {
			return titleImg;
		}
		return Constants.BOS_MANAGEMENT_URL + titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getActiveScope() {
		return activeScope;
	}

	public void setActiveScope(String activeScope) {
		this.activeScope = activeScope;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUnit() {
		return updateUnit;
	}

	public void setUpdateUnit(String updateUnit) {
		this.updateUnit = updateUnit;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		if (description.contains("<img src=\"" + Constants.BOS_MANAGEMENT_URL + "/bos_management")) {
			return description;
		}
		return description.replace("<img src=\"/bos_management", "<img src=\"" + Constants.BOS_MANAGEMENT_URL + "/bos_management");
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
