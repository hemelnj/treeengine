/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.business.treeengine;

import com.ngfs.data.treeengine.GeoTree;
import com.ngfs.data.treeengine.GeoTemplate;
import com.ngfs.data.treeengine.GeoTemplateRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author hemel
 */
@Stateless
public class GeoTemplateBean {

    @Inject
    GeoTemplateRepository hierarchyTemplateRepository;

    public List<GeoTemplate> findAll() {
        List<GeoTemplate> hierarchyItems = new ArrayList<GeoTemplate>();
        hierarchyItems = hierarchyTemplateRepository.findAll();
        return hierarchyItems;
    }

    public GeoTemplate findById(Long Id) {
        GeoTemplate hierarchyNode = new GeoTemplate();
        hierarchyNode = hierarchyTemplateRepository.find(Id);
        return hierarchyNode;
    }

    public GeoTemplate create(GeoTemplate hierarchyNode) {
        Random random = new Random();
        Long x = System.currentTimeMillis() * 2 / 1000L;
        hierarchyNode.setmId(Math.abs(x));
        hierarchyNode.setmCreatedBy("Hemel");
        hierarchyNode.setmCreatedDate(System.currentTimeMillis() / 1000L);
        hierarchyNode.setmModifiedBy("hemel");
        hierarchyNode.setmModifiedDate(System.currentTimeMillis() / 1000L);
        hierarchyTemplateRepository.create(hierarchyNode);
        return hierarchyNode;
    }

    public GeoTemplate edit(GeoTemplate hierarchyNode) {
        GeoTemplate hierarchyModifiedNode = hierarchyTemplateRepository.find(hierarchyNode.getmId());

        hierarchyModifiedNode.setmCode(hierarchyNode.getmCode());
        hierarchyModifiedNode.setmName(hierarchyNode.getmName());
        hierarchyModifiedNode.setmParentId(hierarchyNode.getmParentId());
        hierarchyModifiedNode.setmLevelId(hierarchyNode.getmLevelId());

        hierarchyTemplateRepository.edit(hierarchyModifiedNode);
        return hierarchyModifiedNode;
    }

    public GeoTemplate delete(Long id) {
        GeoTemplate nodeTobeDeleted = hierarchyTemplateRepository.find(id);
        hierarchyTemplateRepository.remove(nodeTobeDeleted);
        return nodeTobeDeleted;
    }

    public List<GeoTemplate> getImmediateChildNode(Long id) {
        TypedQuery<GeoTemplate> query = hierarchyTemplateRepository.getEntityManager().createQuery("SELECT h from GeoTemplate h WHERE h.mParentId = ?1", GeoTemplate.class);
        query.setParameter(1, id);
        List<GeoTemplate> nodes = query.getResultList();
        return nodes;
    }

    public GeoTemplate getParentNode(Long id) {
        TypedQuery<GeoTemplate> query = hierarchyTemplateRepository.getEntityManager().createQuery("select t1 from GeoTemplate t1 where t1.mId=(select t.mParentId from GeoTemplate t where t.mId = ?1)", GeoTemplate.class);
        query.setParameter(1, id);
        GeoTemplate node = query.getSingleResult();
        return node;
    }

    public List<GeoTemplate> getSiblings(Long id) {
        TypedQuery<GeoTemplate> query = hierarchyTemplateRepository.getEntityManager().createQuery("select t1 from Hierarchy t1 where t1.mLevelId =(select t.mLevelId from GeoTemplate t where t.mId= ?1)", GeoTemplate.class);
        query.setParameter(1, id);
        List<GeoTemplate> nodes = query.getResultList();
        return nodes;
    }

    // 
    public List<GeoTemplate> getChildrenNode(Long id) {
        
        Query query = hierarchyTemplateRepository.getEntityManager().createNativeQuery("select t from HIERARCHYTEMPLATE t where t.id <> ?1 connect by prior t.id =  t.parentid start with t.id =?1", GeoTemplate.class);
        query.setParameter(1, id);
        List<GeoTemplate> nodes = query.getResultList();
        return nodes;
    }
}
