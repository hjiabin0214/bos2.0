package cn.hjiabin.bos.domain.take_delivery;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.hjiabin.bos.domain.base.Courier;

/**
 * @description:����
 */
@Entity
@Table(name = "T_WORK_BILL")
public class WorkBill {
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id; // ����
	@Column(name = "C_TYPE")
	private String type; // �������� ��,׷,��
	/*
	 * �µ�:û��ȷ�ϻ���״̬�� ��֪ͨ:�Զ��µ��·����� ��ȷ��:�ӵ�����,�ظ�����ȷ����Ϣ ��ȡ��:�Ѿ�ȡ���ɹ�,����ȷ����Ϣ ���ɹ�����
	 * ��ȡ��:����
	 * 
	 */
	@Column(name = "C_PICKSTATE")
	private String pickstate; // ȡ��״̬
	@Column(name = "C_BUILDTIME")
	private Date buildtime; // ��������ʱ��
	@Column(name = "C_ATTACHBILLTIMES")
	private Integer attachbilltimes; // ׷������
	@Column(name = "C_REMARK")
	private String remark; // ������ע
	@Column(name = "C_SMSNUMBER")
	private String smsNumber; // �������

	@OneToOne
	@JoinColumn(name = "C_COURIER")
	private Courier courier;// ���Ա

	@ManyToOne
	@JoinColumn(name = "C_ORDER_ID")
	private Order order; // ����

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPickstate() {
		return pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	public Date getBuildtime() {
		return buildtime;
	}

	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}

	public Integer getAttachbilltimes() {
		return attachbilltimes;
	}

	public void setAttachbilltimes(Integer attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSmsNumber() {
		return smsNumber;
	}

	public void setSmsNumber(String smsNumber) {
		this.smsNumber = smsNumber;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
