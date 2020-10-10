package cn.plutonight.library.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LPH
 * @since 2020-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Footprint对象", description="")
public class Footprint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    @ApiModelProperty(value = "学校id")
    private String schoolId;

    @ApiModelProperty(value = "自习室编号")
    private String roomNumber;

    @ApiModelProperty(value = "由  row + _ + col 组成")
    private String seatsNumber;

    @ApiModelProperty(value = "存放时间戳差")
    private String stayTime;

    @ApiModelProperty(value = "0: 暂时离开(保留90分钟); 1: 正常坐下(时间选择); -1: 正常离开(结束计时)")
    private Integer status;

    @ApiModelProperty(value = "心情标签 (数组, 用 ,分隔)")
    private String momentTag;

    @ApiModelProperty(value = "坐下时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "学生希望自习的时间")
    private String wantedTime;


}
