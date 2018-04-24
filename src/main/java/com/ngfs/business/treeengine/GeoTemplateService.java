/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.business.treeengine;

import com.ngfs.data.treeengine.GeoTree;
import com.ngfs.data.treeengine.GeoTemplate;
import java.util.List;
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
@Path("/geotemplate")
public class GeoTemplateService {
    @Inject
    GeoTemplateBean hierarchyTemplateBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<GeoTemplate> hierarchyNodeList = hierarchyTemplateBean.findAll();
        GenericEntity<List<GeoTemplate>> hierarchyNodesWrapper = new GenericEntity<List<GeoTemplate>>(hierarchyNodeList) {
        };
        return Response.ok(hierarchyNodesWrapper).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id")Long id) {
        GeoTemplate hierarchyNode = hierarchyTemplateBean.findById(id);
        GenericEntity<GeoTemplate> hierarchyNodeWrapper = new GenericEntity<GeoTemplate>(hierarchyNode) {
        };
        return Response.ok(hierarchyNodeWrapper).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(GeoTemplate hierarchyNode) {
        GeoTemplate heirarchy = hierarchyTemplateBean.create(hierarchyNode);
        GenericEntity<GeoTemplate> hierarchyWrapper = new GenericEntity<GeoTemplate>(heirarchy) {
        };
        return Response.ok(hierarchyWrapper).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(GeoTemplate hierarchy) {
        GeoTemplate hierarchyNode = hierarchyTemplateBean.edit(hierarchy);
        GenericEntity<GeoTemplate> hierarchyWrapper = new GenericEntity<GeoTemplate>(hierarchyNode) {
        };
        return Response.ok(hierarchyWrapper).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        GeoTemplate hierarchyNode = hierarchyTemplateBean.findById(id);
        hierarchyNode = hierarchyTemplateBean.delete(id);
        GenericEntity<GeoTemplate> hierarchyWrapper = new GenericEntity<GeoTemplate>(hierarchyNode) {
        };
        return Response.ok(hierarchyWrapper).build();
    }
}
