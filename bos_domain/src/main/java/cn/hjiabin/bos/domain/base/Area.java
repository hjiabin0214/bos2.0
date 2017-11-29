package cn.hjiabin.bos.domain.base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.struts2.json.annotations.JSON;


/**
 * @description:鍦板煙淇℃伅瀹炰綋绫伙紝涓昏鍖呭惈 鐪佸競鍖�鍘�
 */
@Entity
@Table(name = "T_AREA")
@XmlRootElement(name="area")
public class Area {

	@Id
	@Column(name = "C_ID")
	private String id;
	@Column(name = "C_PROVINCE")
	private String province; // 鐪�
	@Column(name = "C_CITY")
	private String city; // 鍩庡競
	@Column(name = "C_DISTRICT")
	private String district; // 鍖哄煙
	@Column(name = "C_POSTCODE")
	private String postcode; // 閭紪
	@Column(name = "C_CITYCODE")
	private String citycode; // 鍩庡競缂栫爜
	@Column(name = "C_SHORTCODE")
	private String shortcode; // 绠�爜

	@OneToMany(mappedBy = "area")
	private Set<SubArea> subareas = new HashSet<SubArea>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getShortcode() {
		return shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	@JSON(serialize=false)
	public Set<SubArea> getSubareas() {
		return subareas;
	}

	public void setSubareas(Set<SubArea> subareas) {
		this.subareas = subareas;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", province=" + province + ", city=" + city
				+ ", district=" + district + ", postcode=" + postcode
				+ ", citycode=" + citycode + ", shortcode=" + shortcode + "]";
	}

}
