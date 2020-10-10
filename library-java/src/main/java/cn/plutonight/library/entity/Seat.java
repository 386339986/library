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
@ApiModel(value="Seat对象", description="")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "自习室所在学校")
    private String schoolId;

    @ApiModelProperty(value = "自习室名称")
    private String name;

    @ApiModelProperty(value = "自习室编号")
    private String roomNumber;

    @ApiModelProperty(value = "座位位置和状态")
    private String seats;

    @ApiModelProperty(value = "座位总数")
    private Integer seatsCount;

    @ApiModelProperty(value = "剩余座位数量")
    private Integer seatsAvailable;

    @ApiModelProperty(value = "已使用座位数量")
    private Integer seatsUnavailabe;

    @ApiModelProperty(value = "0: 待用; 1: 启用; -1: 维护")
    private Integer status;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;


}
