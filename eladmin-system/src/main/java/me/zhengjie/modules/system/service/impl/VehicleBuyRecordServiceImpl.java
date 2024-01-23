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
package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.VehicleBuyRecord;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.VehicleBuyRecordRepository;
import me.zhengjie.modules.system.service.VehicleBuyRecordService;
import me.zhengjie.modules.system.service.dto.VehicleBuyRecordDto;
import me.zhengjie.modules.system.service.dto.VehicleBuyRecordQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.VehicleBuyRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author Sean
* @date 2023-03-20
**/
@Service
@RequiredArgsConstructor
public class VehicleBuyRecordServiceImpl implements VehicleBuyRecordService {

    private final VehicleBuyRecordRepository vehicleBuyRecordRepository;
    private final VehicleBuyRecordMapper vehicleBuyRecordMapper;

    private final UserService userService;

    @Override
    public PageResult<VehicleBuyRecordDto> queryAll(VehicleBuyRecordQueryCriteria criteria, Pageable pageable){
        Page<VehicleBuyRecord> page = vehicleBuyRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(vehicleBuyRecordMapper::toDtoWithDetails));
    }

    @Override
    public List<VehicleBuyRecordDto> queryAll(VehicleBuyRecordQueryCriteria criteria){
        return vehicleBuyRecordMapper.toDto(vehicleBuyRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public VehicleBuyRecordDto findById(Integer id) {
        VehicleBuyRecord vehicleBuyRecord = vehicleBuyRecordRepository.findById(id).orElseGet(VehicleBuyRecord::new);
        ValidationUtil.isNull(vehicleBuyRecord.getId(),"VehicleBuyRecord","id",id);
        return vehicleBuyRecordMapper.toDto(vehicleBuyRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VehicleBuyRecordDto create(VehicleBuyRecord resources) {
        return vehicleBuyRecordMapper.toDto(vehicleBuyRecordRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleBuyRecord resources) {
        VehicleBuyRecord vehicleBuyRecord = vehicleBuyRecordRepository.findById(resources.getId()).orElseGet(VehicleBuyRecord::new);
        ValidationUtil.isNull( vehicleBuyRecord.getId(),"VehicleBuyRecord","id",resources.getId());
        vehicleBuyRecord.copy(resources);
        vehicleBuyRecordRepository.save(vehicleBuyRecord);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            vehicleBuyRecordRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<VehicleBuyRecordDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (VehicleBuyRecordDto vehicleBuyRecord : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("来源", vehicleBuyRecord.getSource());
            map.put("价格", vehicleBuyRecord.getPrice());
            map.put("用户id", vehicleBuyRecord.getUserId());
            map.put("车辆类型", vehicleBuyRecord.getVehicleType());
            map.put("车牌号码", vehicleBuyRecord.getLicensePlate());
            map.put("购买时间", vehicleBuyRecord.getBuyTime());
            map.put("垫付方式", vehicleBuyRecord.getBuyType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
