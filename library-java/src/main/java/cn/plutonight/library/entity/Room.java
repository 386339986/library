package cn.plutonight.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 自习室
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Room对象", description="自习室")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自习室id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学校id")
    private String school_id;

    @ApiModelProperty(value = "自习室所在校区")
    private String campus;

    @ApiModelProperty(value = "自习室名称")
    private String name;

    @ApiModelProperty(value = "状态：1 启用 2 待用 3 维护")
    private Integer status;

    @ApiModelProperty(value = "自习室剩余可用数量")
    private Integer available;

    @ApiModelProperty(value = "自习室总座位数量")
    private Integer count;

    @ApiModelProperty(value = "自习室座位和状态")
    private String seats;


}
