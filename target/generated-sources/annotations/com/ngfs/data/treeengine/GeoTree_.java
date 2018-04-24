package com.ngfs.data.treeengine;

import com.ngfs.common.NgfsBase_;
import com.ngfs.data.treeengine.GeoTemplate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20140809-rNA", date="2018-04-24T18:59:25")
@StaticMetamodel(GeoTree.class)
public class GeoTree_ extends NgfsBase_ {

    public static volatile SingularAttribute<GeoTree, Integer> mLevelId;
    public static volatile SingularAttribute<GeoTree, GeoTemplate> geoTemplate;
    public static volatile SingularAttribute<GeoTree, String> mCode;
    public static volatile SingularAttribute<GeoTree, Long> mParentId;
    public static volatile SingularAttribute<GeoTree, String> mName;

}