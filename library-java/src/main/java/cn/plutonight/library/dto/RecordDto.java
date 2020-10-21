package cn.plutonight.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecordDto {

    @ApiModelProperty(value = "自习室名称", required = true)
    private String name;

    @ApiModelProperty(value = "学习时长", required = true)
    private Integer time;

    @ApiModelProperty(value = "日期", required = true)
    private String date;

}
