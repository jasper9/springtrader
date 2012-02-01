// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.springframework.nanotrader.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.nanotrader.domain.Quote;

privileged aspect Quote_Roo_Jpa_Entity {
    
    declare @type: Quote: @Entity;
    
    declare @type: Quote: @Table(schema = "public", name = "quote");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quoteid")
    private Integer Quote.quoteid;
    
    public Integer Quote.getQuoteid() {
        return this.quoteid;
    }
    
    public void Quote.setQuoteid(Integer id) {
        this.quoteid = id;
    }
    
}
