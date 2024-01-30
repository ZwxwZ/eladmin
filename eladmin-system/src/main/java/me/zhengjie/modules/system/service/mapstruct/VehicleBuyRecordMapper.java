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
package me.zhengjie.modules.system.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.domain.VehicleBuyRecord;
import me.zhengjie.modules.system.service.dto.VehicleBuyRecordDto;
import me.zhengjie.service.mapstruct.LocalStorageMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author Sean
 * @website https://eladmin.vip
 * @date 2023-03-20
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleBuyRecordMapper extends BaseMapper<VehicleBuyRecordDto, VehicleBuyRecord> {

    @Named("toDtoWithDetails")
    default VehicleBuyRecordDto toDtoWithDetails(VehicleBuyRecord record) {
        VehicleBuyRecordDto userDto = this.toDto(record);

        User u = Optional.ofNullable(record.getUser()).orElse(new User());
        userDto.setUserName(u.getUsername());
        userDto.setDepartment(Optional.ofNullable(u.getDept()).orElse(new Dept()).getName());
        return userDto;
    }

}
