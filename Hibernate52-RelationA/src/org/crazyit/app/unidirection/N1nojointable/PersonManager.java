package org.crazyit.app.unidirection.N1nojointable;

import org.crazyit.app.unidirection.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Description: <br/>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> <br/>
 * Copyright (C), 2001-2016, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PersonManager {
	public static void main(String[] args) {
		PersonManager mgr = new PersonManager();
		mgr.testCascase();
		HibernateUtil.sessionFactory.close();
	}

	private void testCascase() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		// 创建一个Person对象
		Person p = new Person();
		// 创建一个瞬态的Address对象
		Address a = new Address("广州天河"); // ①
		// 设置Person的name为crazyit.org字符串
		p.setName("crazyit.org");
		p.setAge(21);
		// 设置Person和Address之间的关联关系
		p.setAddress(a);
		// 持久化Person对象
		session.persist(p);
		// 创建一个瞬态的Address对象
		Address a2 = new Address("上海虹口"); // ②
		// 修改持久化状态的Person对象
		p.setAddress(a2); // ③
		tx.commit();
		HibernateUtil.closeSession();
	}

	private void testPerson() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		// 创建一个Person对象
		Person p = new Person();
		// 设置Person的name为crazyit字符串
		p.setName("crazyit");
		p.setAge(21);
		// 持久化Person对象(对应于插入主表记录)
		session.save(p);
		// 创建一个瞬态的Address对象
		Address a = new Address("广州天河"); // ①
		// 先设置Person和Address之间的关联关系
		p.setAddress(a);
		// 再持久化Address对象(对应于插入从表记录)
		session.persist(a);
		// 创建一个瞬态的Address对象
		Address a2 = new Address("上海虹口"); // ②
		// 先设置Person和Address之间的关联关系
		p.setAddress(a2);
		// 再持久化Address对象(对应于插入从表记录)
		session.persist(a2); // ③
		tx.commit();
		HibernateUtil.closeSession();
	}
}
