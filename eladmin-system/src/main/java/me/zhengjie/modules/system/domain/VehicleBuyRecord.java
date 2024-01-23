/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author Sean
* @date 2023-03-20
**/
@Entity
@Data
@Table(name="vehicle_buy_record")
public class VehicleBuyRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "`source`")
    @ApiModelProperty(value = "来源")
    private String source;

    @Column(name = "`price`")
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

/*    @Column(name = "`user_id`")
    @ApiModelProperty(value = "用户id")
    private Long userId;*/

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "`vehicle_type`")
    @ApiModelProperty(value = "车辆类型")
    private String vehicleType;

    @Column(name = "`license_plate`")
    @ApiModelProperty(value = "车牌号码")
    private String licensePlate;

    @Column(name = "`buy_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "购买时间")
    private Timestamp buyTime;

    @Column(name = "`buy_type`")
    @ApiModelProperty(value = "垫付方式")
    private String buyType;

    public void copy(VehicleBuyRecord source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
