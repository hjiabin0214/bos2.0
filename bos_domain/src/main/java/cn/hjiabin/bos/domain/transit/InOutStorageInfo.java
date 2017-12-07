package cn.hjiabin.bos.domain.transit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: �������Ϣ
 */
@Entity
@Table(name = "T_IN_OUT_STORAGE_INFO")
public class InOutStorageInfo {
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id;

	@Column(name = "C_OPERATION")
	private String operation; // �������� �� ��⡢���⡢��������

	@Column(name = "C_ADDRESS")
	private String Address; // �ֿ⡢���� ��ַ

	@Column(name = "C_DESCRIPTION")
	private String description; // ����

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
