package org.crazyit.app.unidirection.oneone.jointable;

import org.crazyit.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class PersonManager
{
	public static void main(String[] args)
	{
		PersonManager mgr = new PersonManager();
		mgr.testPerson();
		HibernateUtil.sessionFactory.close();
	}

	private void testPerson()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		// 创建一个Person对象
		Person p = new Person();
		// 设置Person的name为crazyit字符串
		p.setName("crazyit");
		p.setAge(21);

		// 创建一个瞬态的Address对象
		Address a = new Address("广州天河");
		// 设置Person和Address之间的关联关系
		p.setAddress(a);
		// 持久化Address对象
		session.persist(a);
		// 持久化Person对象
		session.save(p);
		tx.commit();
		HibernateUtil.closeSession();
	}
}
