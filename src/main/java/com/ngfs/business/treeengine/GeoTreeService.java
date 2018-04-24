/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.business.treeengine;

import com.ngfs.data.treeengine.GeoTree;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author hemel
 */
@Stateless
@Path("/geotree")
public class GeoTreeService {

    @Inject
    GeoTreeBean hierarchyBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<GeoTree> hierarchyNodeList = hierarchyBean.findAll();
        GenericEntity<List<GeoTree>> hierarchyNodesWrapper = new GenericEntity<List<GeoTree>>(hierarchyNodeList) {
        };
        return Response.ok(hierarchyNodesWrapper).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id")Long id) {
        GeoTree hierarchyNode = hierarchyBean.findById(id);
        GenericEntity<GeoTree> hierarchyNodeWrapper = new GenericEntity<GeoTree>(hierarchyNode) {
        };
        return Response.ok(hierarchyNodeWrapper).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(GeoTree hierarchyNode) {
        GeoTree heirarchy = hierarchyBean.create(hierarchyNode);
        GenericEntity<GeoTree> hierarchyWrapper = new GenericEntity<GeoTree>(heirarchy) {
        };
        return Response.ok(hierarchyWrapper).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(GeoTree hierarchy) {
        GeoTree hierarchyNode = hierarchyBean.edit(hierarchy);
        GenericEntity<GeoTree> hierarchyWrapper = new GenericEntity<GeoTree>(hierarchyNode) {
        };
        return Response.ok(hierarchyWrapper).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        
        GeoTree hierarchyNode = hierarchyBean.delete(id);
        GenericEntity<GeoTree> hierarchyWrapper = new GenericEntity<GeoTree>(hierarchyNode) {
        };
        return Response.ok(hierarchyWrapper).build();
    }
    
    @GET
    @Path("/immediatechild/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImmediateChildNode(@PathParam("id")Long id) {
        List<GeoTree> hierarchyNode = hierarchyBean.getImmediateChildNode(id);
        GenericEntity<List<GeoTree>> hierarchyNodeWrapper = new GenericEntity<List<GeoTree>>(hierarchyNode) {
        };
        return Response.ok(hierarchyNodeWrapper).build();
    }
    
    @GET
    @Path("/parentnode/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParentNode(@PathParam("id")Long id) {
        GeoTree hierarchyNode = hierarchyBean.getParentNode(id);
        GenericEntity<GeoTree> hierarchyNodeWrapper = new GenericEntity<GeoTree>(hierarchyNode) {
        };
        return Response.ok(hierarchyNodeWrapper).build();
    }
    
    @GET
    @Path("/siblings/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSiblings(@PathParam("id")Long id) {
        List<GeoTree> hierarchyNode = hierarchyBean.getSiblings(id);
        GenericEntity<List<GeoTree>> hierarchyNodeWrapper = new GenericEntity<List<GeoTree>>(hierarchyNode) {
        };
        return Response.ok(hierarchyNodeWrapper).build();
    }
    
    
    @GET
    @Path("/childrennode/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChildrenNode(@PathParam("id")Long id) {
        List<GeoTree> hierarchyNode = hierarchyBean.getChildrenNode(id);
        GenericEntity<List<GeoTree>> hierarchyNodeWrapper = new GenericEntity<List<GeoTree>>(hierarchyNode) {
        };
        return Response.ok(hierarchyNodeWrapper).build();
    }
}
