package cn.hjiabin.bos.domain.take_delivery;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

import com.alibaba.fastjson.annotation.JSONType;

import cn.hjiabin.bos.domain.base.Area;

/**
 * @description:�˵�ʵ����
 */
@Entity
@Table(name = "T_WAY_BILL")
public class WayBill implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id;
	@Column(name = "C_WAY_BILL_NUM", unique = true)
	private String wayBillNum; // �˵����
	@OneToOne
	@JoinColumn(name = "C_ORDER_ID")
	private Order order; // ������Ϣ

	@Column(name = "C_SEND_NAME")
	private String sendName; // �ļ�������
	@Column(name = "C_SEND_MOBILE")
	private String sendMobile;// �ļ��˵绰
	@Column(name = "C_SEND_COMPANY")
	private String sendCompany;// �ļ��˹�˾
	@OneToOne
	@JoinColumn(name = "C_SEND_AREA_ID")
	private Area sendArea; // �ļ���ʡ������Ϣ
	@Column(name = "C_SEND_ADDRESS")
	private String sendAddress;// �ļ�����ϸ��ַ��Ϣ

	@Column(name = "C_REC_NAME")
	private String recName;// �ռ�������
	@Column(name = "C_REC_MOBILE")
	private String recMobile;// �ռ��˵绰
	@Column(name = "C_REC_COMPANY")
	private String recCompany;// �ռ��˹�˾
	@OneToOne
	@JoinColumn(name = "C_REC_AREA_ID")
	private Area recArea; // �ռ���ʡ������Ϣ
	@Column(name = "C_REC_ADDRESS")
	private String recAddress;// �ռ�����ϸ��ַ��Ϣ

	@Column(name = "C_SEND_PRO_NUM")
	private String sendProNum; // ��ݲ�Ʒ���ͱ�ţ����˵��ա����˴��ա����˸���
	@Column(name = "C_GOODS_TYPE")
	private String goodsType;// �м������ͣ��ļ����·� ��ʳƷ��������Ʒ
	@Column(name = "C_PAY_TYPE_NUM")
	private String payTypeNum;// ֧�����ͱ�ţ��ĸ��սᡢ�ĸ��½ᡢ����
	@Column(name = "C_WEIGHT")
	private Double weight;// �м�������
	@Column(name = "C_REMARK")
	private String remark; // ��ע
	@Column(name = "C_NUM")
	private Integer num; // ԭ����

	@Column(name = "C_ARRIVE_CITY")
	private String arriveCity; // �����

	@Column(name = "C_FEEITEMNUM")
	private Integer feeitemnum; // ʵ�ʼ���
	@Column(name = "C_ACTLWEIT")
	private Double actlweit; // ʵ������
	@Column(name = "C_VOL")
	private String vol; // ��� �����ʽΪ1*1*1*1����ʾ��*��*��*����
	@Column(name = "C_FLOADREQR")
	private String floadreqr; // ����Ҫ�� (����¼��1=�ޣ�2=������4=��������·)

	@Column(name = "C_WAY_BILL_TYPE")
	private String wayBillType; // �˵����ͣ����Ͱ������������ݡ��쵥��������
	/*
	 * �˵�״̬�� �������������С���ǩ�ա��쳣
	 */
	@Column(name = "C_SIGN_STATUS")
	private Integer signStatus; // ǩ��״̬

	/*
	 * 1�������޸ĵ���״̬Ϊ���� 2������ʱ�轫״̬��Ϊ���ǡ� 3��ȡ������ʱ��Ҫ��״̬��Ϊ����
	 */
	@Column(name = "C_DELTAG")
	private String delTag; // ���ϱ�־

	public String getArriveCity() {
		return arriveCity;
	}

	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWayBillNum() {
		return wayBillNum;
	}

	public void setWayBillNum(String wayBillNum) {
		this.wayBillNum = wayBillNum;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendMobile() {
		return sendMobile;
	}

	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}

	public String getSendCompany() {
		return sendCompany;
	}

	public void setSendCompany(String sendCompany) {
		this.sendCompany = sendCompany;
	}

	public Area getSendArea() {
		return sendArea;
	}

	public void setSendArea(Area sendArea) {
		this.sendArea = sendArea;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getRecMobile() {
		return recMobile;
	}

	public void setRecMobile(String recMobile) {
		this.recMobile = recMobile;
	}

	public String getRecCompany() {
		return recCompany;
	}

	public void setRecCompany(String recCompany) {
		this.recCompany = recCompany;
	}

	public Area getRecArea() {
		return recArea;
	}

	public void setRecArea(Area recArea) {
		this.recArea = recArea;
	}

	public String getRecAddress() {
		return recAddress;
	}

	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}

	public String getSendProNum() {
		return sendProNum;
	}

	public void setSendProNum(String sendProNum) {
		this.sendProNum = sendProNum;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getPayTypeNum() {
		return payTypeNum;
	}

	public void setPayTypeNum(String payTypeNum) {
		this.payTypeNum = payTypeNum;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getFeeitemnum() {
		return feeitemnum;
	}

	public void setFeeitemnum(Integer feeitemnum) {
		this.feeitemnum = feeitemnum;
	}

	public Double getActlweit() {
		return actlweit;
	}

	public void setActlweit(Double actlweit) {
		this.actlweit = actlweit;
	}

	public String getVol() {
		return vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getFloadreqr() {
		return floadreqr;
	}

	public void setFloadreqr(String floadreqr) {
		this.floadreqr = floadreqr;
	}

	public String getWayBillType() {
		return wayBillType;
	}

	public void setWayBillType(String wayBillType) {
		this.wayBillType = wayBillType;
	}

	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	public String getDelTag() {
		return delTag;
	}

	public void setDelTag(String delTag) {
		this.delTag = delTag;
	}

}
