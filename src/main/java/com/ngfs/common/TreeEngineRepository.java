/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.common;

import com.ngfs.core.AbstractRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hemel
 * @param <T>
 */


public abstract class TreeEngineRepository<T> extends AbstractRepository<T> {

    @PersistenceContext(unitName = "TreeEnginePU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    public TreeEngineRepository(Class<T> entityClass) {
        super(entityClass);
    }
    
}
