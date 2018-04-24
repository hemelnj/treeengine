/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.data.treeengine;

import com.ngfs.common.NgfsBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author hemel
 */
@Entity
public class GeoTree extends NgfsBase {

    @Column(name = "Name", nullable = false)
    private String mName;
    @Column(name = "Code", nullable = false)
    private String mCode;
    @Column(name = "ParentId", nullable = false, length = 30)
    private Long mParentId;
    @Column(name = "LevelId", nullable = false)
    private Integer mLevelId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GeoTemplateId")
    private GeoTemplate geoTemplate;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCode() {
        return mCode;
    }

    public GeoTemplate getGeoTemplate() {
        return geoTemplate;
    }

    public void setGeoTemplate(GeoTemplate geoTemplate) {
        this.geoTemplate = geoTemplate;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public Long getmParentId() {
        return mParentId;
    }

    public void setmParentId(Long mParentId) {
        this.mParentId = mParentId;
    }

    public Integer getmLevelId() {
        return mLevelId;
    }

    public void setmLevelId(Integer mLevelId) {
        this.mLevelId = mLevelId;
    }

    public Long getmId() {
        return mId;
    }

    /**
     *
     * @param mId
     */
    @Override
    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(String mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }

    public Long getmCreatedDate() {
        return mCreatedDate;
    }

    public void setmCreatedDate(Long mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }

    public String getmModifiedBy() {
        return mModifiedBy;
    }

    public void setmModifiedBy(String mModifiedBy) {
        this.mModifiedBy = mModifiedBy;
    }

    public long getmModifiedDate() {
        return mModifiedDate;
    }

    public void setmModifiedDate(long mModifiedDate) {
        this.mModifiedDate = mModifiedDate;
    }

}
