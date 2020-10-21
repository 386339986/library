package cn.plutonight.library.controller;


import cn.plutonight.library.dto.ViolationDto;
import cn.plutonight.library.entity.Violation;
import cn.plutonight.library.service.IViolationService;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import cn.plutonight.library.utils.ToolUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LPH
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/violation")
public class ViolationController {

    @Autowired
    IViolationService violationService;

    @ApiOperation(value = "record", notes = "违规记录")
    @GetMapping("/record")
    public ResponseMsg record() {
        // MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Violation> violationList = violationService.list(new QueryWrapper<Violation>().eq("student_id", 1));

        List<ViolationDto> recordDtoList = new ArrayList<>();

        for (Violation violation: violationList) {
            ViolationDto violationDto = new ViolationDto();
            violationDto.setReason(violation.getReason());
            violationDto.setTime(ToolUtils.timestampToDataString(violation.getCreateTime().getTime()));
            recordDtoList.add(violationDto);
        }

        String result = JSON.toJSONString(recordDtoList);

        return ResponseGenerator.getSuccessResponse(result);
    }
}
