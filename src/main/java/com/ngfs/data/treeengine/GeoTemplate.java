/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.data.treeengine;

import com.ngfs.common.NgfsBase;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author hemel
 */
@Entity
public class GeoTemplate extends NgfsBase{
    
    @Column(name="Name",nullable=false)    
    private String mName;
    @Column(name="Code",nullable=false)
    private String mCode;
    @Column(name="ParentId",nullable=false,length=30)
    private Long mParentId;
    @Column(name="LevelId",nullable=false)
    private Integer mLevelId;

    

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCode() {
        return mCode;
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
  
}
