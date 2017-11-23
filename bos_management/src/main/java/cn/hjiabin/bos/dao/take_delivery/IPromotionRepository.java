package cn.hjiabin.bos.dao.take_delivery;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hjiabin.bos.domain.take_delivery.Promotion;

public interface IPromotionRepository extends JpaRepository<Promotion, Integer> {

	@Query("update Promotion set status = '2' where endDate < ? and status = '1'")
	@Modifying
	void updateStatus(Date date);
	
}
