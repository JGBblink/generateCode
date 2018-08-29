package com.shangyi.${project}.dao.entity${module};

import lombok.Data;

import javax.persistence.*;

/**
 * TODO Description
 *
 * @author JinGuiBo
 * @date 2018/6/28.
 */
@Entity
@Data
@Table(name = "${table}")
public class ${className}Entity {

    @Id
    <#list columns as column >
    /**
     * TODO
     */
    @Column(name = "${column.table}")
    private ${column.type} ${column.name};

    </#list>

}
