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
 * 
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Seat对象", description="")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "座位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    @ApiModelProperty(value = "学校id")
    private Integer schoolId;

    @ApiModelProperty(value = "自习室id")
    private Integer roomId;

    @ApiModelProperty(value = "状态：1 空闲 2 使用 3 正常使用 ")
    private Integer status;


}
