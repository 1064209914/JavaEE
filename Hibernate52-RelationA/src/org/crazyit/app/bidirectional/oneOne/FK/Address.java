package org.crazyit.app.bidirectional.oneOne.FK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="address_inf")
@Getter@Setter
public class Address
{
	// 标识属性
	@Id @Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	// 定义地址详细信息的成员变量
	private String addressDetail;
	// 定义该Address实体关联的Person实体
	@OneToOne(targetEntity=Person.class)
	// 用于映射person_id外键列，参照person_inf表的person_id列
	// 指定了unique=true表明是1-1关联
	@JoinColumn(name="person_id" , referencedColumnName="person_id"
		, unique=true)
	private Person person;

	// 无参数的构造器
	public Address()
	{
	}
	// 初始化全部成员变量的构造器
	public Address(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}

}