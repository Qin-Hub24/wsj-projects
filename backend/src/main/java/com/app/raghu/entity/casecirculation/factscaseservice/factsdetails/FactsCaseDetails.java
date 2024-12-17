package com.app.raghu.entity.casecirculation.factscaseservice.factsdetails;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/***
 * 模块名称：案情模块
 * 作者：莫康为、钟晓欣、唐永宏
 * 修改日期：20241112
 * 说明：案情模块接口实现
 * */
@Entity
@Table(name = "facts_case_details")
@Data
public class FactsCaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String caseNumber;

    private String title;

    private String description;

    private String status;

    private Integer currentCourtId;

    private Date createdAt;

    private Date updatedAt;

    private Integer createdBy;

    private Integer updatedBy;
}