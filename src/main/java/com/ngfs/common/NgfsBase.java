/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngfs.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

/**
 *
 * @author hemel
 */
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public class NgfsBase {
    @Id
    @GeneratedValue
    @Column(name="Id",length=30)
    protected Long mId;
    @Column(name="CreatedBy",nullable=false)
    protected String mCreatedBy;
    @Column(name="CreatedDate",nullable=false,length=30)
    protected Long mCreatedDate;
    @Column(name="ModifiedBy",nullable=false)
    protected String mModifiedBy;
    @Column(name="ModifiedDate",nullable=false,length=30)
    protected Long mModifiedDate;

    public Long getmId() {
        return mId;
    }

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
