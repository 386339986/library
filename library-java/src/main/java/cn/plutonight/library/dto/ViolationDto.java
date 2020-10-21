package cn.plutonight.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ViolationDto {

    @ApiModelProperty(value = "违规原因", required = true)
    private String reason;

    @ApiModelProperty(value = "违规时间", required = true)
    private String time;

}
