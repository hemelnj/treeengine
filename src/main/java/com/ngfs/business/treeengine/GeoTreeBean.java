/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.business.treeengine;

import com.ngfs.common.Utility;
import com.ngfs.data.treeengine.GeoTemplate;
import com.ngfs.data.treeengine.GeoTemplateRepository;
import com.ngfs.data.treeengine.GeoTree;
import com.ngfs.data.treeengine.GeoTreeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static javafx.scene.input.KeyCode.T;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;

/**
 *
 * @author hemel
 */
@Stateless
public class GeoTreeBean {

    @Inject
    GeoTreeRepository hierarchyRepository;
    @Inject
    GeoTemplateRepository geoTemplateRepository;
    //@Inject
    //Random random; 
    List<GeoTree> childrenNode;

    public List<GeoTree> findAll() {
        List<GeoTree> hierarchyItems = new ArrayList<GeoTree>();
        hierarchyItems = hierarchyRepository.findAll();
        return hierarchyItems;
    }

    public GeoTree findById(Long Id) {
        GeoTree hierarchyNode = new GeoTree();
        hierarchyNode = hierarchyRepository.find(Id);
        return hierarchyNode;
    }

    public GeoTree create(GeoTree hierarchyNode) {
        Random random = new Random();
        Long x = System.currentTimeMillis() * 2 / 100000L;
        hierarchyNode.setmId(Math.abs(x));
        hierarchyNode.setmCreatedBy("Hemel");
        hierarchyNode.setmCreatedDate(System.currentTimeMillis() / 1000L);
        hierarchyNode.setmModifiedBy("hemel");
        hierarchyNode.setmModifiedDate(System.currentTimeMillis() / 1000L);
        GeoTemplate geoTemplate = geoTemplateRepository.find(hierarchyNode.getGeoTemplate().getmId());
        hierarchyNode.setGeoTemplate(geoTemplate);
        hierarchyRepository.create(hierarchyNode);
        return hierarchyNode;
    }

    public GeoTree edit(GeoTree hierarchyNode) {
        GeoTree hierarchyModifiedNode = hierarchyRepository.find(hierarchyNode.getmId());

        hierarchyModifiedNode.setmCode(hierarchyNode.getmCode());
        hierarchyModifiedNode.setmName(hierarchyNode.getmName());
        GeoTemplate geoTemplate = geoTemplateRepository.find(hierarchyNode.getmParentId());
        hierarchyModifiedNode.setGeoTemplate(geoTemplate);
        //hierarchyModifiedNode.setmParentId(hierarchyNode.getmParentId());
        hierarchyModifiedNode.setmLevelId(hierarchyNode.getmLevelId());

        hierarchyRepository.edit(hierarchyModifiedNode);
        return hierarchyModifiedNode;
    }

    public GeoTree delete(Long id) {
        GeoTree nodeTobeDeleted = hierarchyRepository.find(id);
        createOrphanNode(id);
        hierarchyRepository.remove(nodeTobeDeleted);
        return nodeTobeDeleted;
    }

    public void createOrphanNode(Long id) {
        List<GeoTree> ImmediateChildrenNode = getImmediateChildNode(id);
        for (GeoTree geoTreeNode : ImmediateChildrenNode) {
            geoTreeNode.setmParentId(Utility.orphanNodeValue);
            hierarchyRepository.edit(geoTreeNode);
        }
    }

    public List<GeoTree> getAllOrphanNode() {
        TypedQuery<GeoTree> query = hierarchyRepository.getEntityManager().createQuery("SELECT h from GeoTree h WHERE h.mParentId = ?1", GeoTree.class);
        query.setParameter(1, Utility.orphanNodeValue);
        List<GeoTree> nodes = query.getResultList();
        return nodes;
    }

    public List<GeoTree> getImmediateChildNode(Long id) {
        TypedQuery<GeoTree> query = hierarchyRepository.getEntityManager().createQuery("SELECT h from GeoTree h WHERE h.mParentId = ?1", GeoTree.class);
        query.setParameter(1, id);
        List<GeoTree> nodes = query.getResultList();
        return nodes;
    }

    public GeoTree getParentNode(Long id) {
        TypedQuery<GeoTree> query = hierarchyRepository.getEntityManager().createQuery("select t1 from GeoTree t1 where t1.mId=(select t.mParentId from GeoTree t where t.mId = ?1)", GeoTree.class);
        query.setParameter(1, id);
        GeoTree node = query.getSingleResult();
        return node;
    }

    public List<GeoTree> getSiblings(Long id) {
        TypedQuery<GeoTree> query = hierarchyRepository.getEntityManager().createQuery("select t1 from GeoTree t1 where t1.mLevelId =(select t.mLevelId from GeoTree t where t.mId= ?1)", GeoTree.class);
        query.setParameter(1, id);
        List<GeoTree> nodes = query.getResultList();
        return nodes;
    }

    // 
    /**
     *
     * @param id
     * @return
     */
    public List<GeoTree> getChildrenNode(Long id) {
        childrenNode = new ArrayList<GeoTree>();
        treeTraverse(id);

        return childrenNode;
    }

    public void treeTraverse(Long id) {

        List<GeoTree> nodes = getImmediateChildNode(id);
        if (nodes.size() > 0) {
        for(GeoTree node : nodes){
        childrenNode.add(node);
        List<GeoTree> nodeList = getImmediateChildNode(node.getmId());
            if (!nodeList.isEmpty()) {
                treeTraverse(node.getmId());
            }
        }
        } else {
            GeoTree node = findById(id);
            childrenNode.add(node);
        }

    }

}
