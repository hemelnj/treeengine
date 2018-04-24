/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.core;

import com.ngfs.common.TreeEngineRepository;
import com.ngfs.data.treeengine.GeoTree;
import com.ngfs.data.treeengine.GeoTreeRepository;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author hemel
 */
public class InitalDBConfiguration implements ServletContextListener {
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        TreeEngineRepository<GeoTree> em = new GeoTreeRepository();
//        

 Number cnt = (Number) em.getEntityManager().createQuery("select count(d) from HIERARCHY d").getSingleResult();

    if(cnt.intValue() == 0){
     em.getEntityManager().getTransaction().begin();
        em.getEntityManager().createNativeQuery("insert into HIERARCHY (ID, CODE, CREATEDBY, CREATEDDATE, LEVELID, MODIFIEDBY, MODIFIEDDATE, NAME, PARENTID) values (1, 'root', 'Hemel', 1523795314, 1, 'hemel', 1523795314, 'Root', 0);").executeUpdate();
        em.getEntityManager().getTransaction().commit();
    }
    }
}
