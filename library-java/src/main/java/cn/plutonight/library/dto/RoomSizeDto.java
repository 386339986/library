package cn.plutonight.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoomSizeDto {

    @ApiModelProperty(value = "自习室列宽", required = true)
    private Integer col;

    @ApiModelProperty(value = "自习室座行宽", required = true)
    private Integer row;
}
