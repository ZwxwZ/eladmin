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
package me.zhengjie.modules.system.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.system.domain.VehicleBuyRecord;
import me.zhengjie.modules.system.service.VehicleBuyRecordService;
import me.zhengjie.modules.system.service.dto.VehicleBuyRecordQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://eladmin.vip
* @author Sean
* @date 2023-03-20
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "vehicleBuyRecordService管理")
@RequestMapping("/api/vehicleBuyRecord")
public class VehicleBuyRecordController {

    private final VehicleBuyRecordService vehicleBuyRecordService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('vehicleBuyRecord:list')")
    public void exportVehicleBuyRecord(HttpServletResponse response, VehicleBuyRecordQueryCriteria criteria) throws IOException {
        vehicleBuyRecordService.download(vehicleBuyRecordService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询vehicleBuyRecordService")
    @ApiOperation("查询vehicleBuyRecordService")
    @PreAuthorize("@el.check('vehicleBuyRecord:list')")
    public ResponseEntity<Object> queryVehicleBuyRecord(VehicleBuyRecordQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(vehicleBuyRecordService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增vehicleBuyRecordService")
    @ApiOperation("新增vehicleBuyRecordService")
    @PreAuthorize("@el.check('vehicleBuyRecord:add')")
    public ResponseEntity<Object> createVehicleBuyRecord(@Validated @RequestBody VehicleBuyRecord resources){
        return new ResponseEntity<>(vehicleBuyRecordService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改vehicleBuyRecordService")
    @ApiOperation("修改vehicleBuyRecordService")
    @PreAuthorize("@el.check('vehicleBuyRecord:edit')")
    public ResponseEntity<Object> updateVehicleBuyRecord(@Validated @RequestBody VehicleBuyRecord resources){
        vehicleBuyRecordService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除vehicleBuyRecordService")
    @ApiOperation("删除vehicleBuyRecordService")
    @PreAuthorize("@el.check('vehicleBuyRecord:del')")
    public ResponseEntity<Object> deleteVehicleBuyRecord(@RequestBody Integer[] ids) {
        vehicleBuyRecordService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
