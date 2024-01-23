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
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @website https://eladmin.vip
* @author Sean
* @date 2023-03-20
**/
@Data
public class VehicleBuyRecordQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String source;

    /** 精确 */
    @Query
    private Long userId;

    @Query(propName = "username", type = Query.Type.INNER_LIKE, joinName = "user")
    private String userName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String vehicleType;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String licensePlate;

    /** 精确 */
    @Query
    private String buyType;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<BigDecimal> price;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> buyTime;
}
