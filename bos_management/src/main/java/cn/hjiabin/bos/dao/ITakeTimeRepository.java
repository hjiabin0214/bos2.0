package cn.hjiabin.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.base.TakeTime;

public interface ITakeTimeRepository extends JpaRepository<TakeTime, Integer> {

}
