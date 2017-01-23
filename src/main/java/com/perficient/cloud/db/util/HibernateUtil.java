package com.perficient.cloud.db.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class HibernateUtil {
	
	public List<String> getEmails(Session session, String groupName){
//		List list = session.createCriteria(Member.class)
//		    .createCriteria("group").add(Restrictions.eq("group", 1))
//		        .add( Restrictions.eq("groupname", group))
//		    .list();
		
		//HQL
		List<String> list;
		Query q= session.createQuery("SELECT m.email FROM Member AS m "  
				+ "JOIN m.groups AS g " 
				+ "WHERE g.groupName=:groupname ");
		q.setString("groupname", groupName);
		list = q.list();
		
		return list;
	}
}
