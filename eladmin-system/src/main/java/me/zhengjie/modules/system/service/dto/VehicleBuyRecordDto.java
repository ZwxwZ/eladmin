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
package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.service.dto.LocalStorageDto;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @website https://eladmin.vip
* @description /
* @author Sean
* @date 2023-03-20
**/
@Data
public class VehicleBuyRecordDto implements Serializable {

    /** ID */
    private Integer id;

    /** 来源 */
    private String source;

    /** 价格 */
    private BigDecimal price;

    /** 用户id */
    private Long userId;

    private String userName;

    private String department;

    /** 车辆类型 */
    private String vehicleType;

    /** 车牌号码 */
    private String licensePlate;

    /** 购买时间 */
    private Timestamp buyTime;

    /** 垫付方式 */
    private String buyType;

    private String imgPath;

    private List<LocalStorageDto> localStorages;
}
