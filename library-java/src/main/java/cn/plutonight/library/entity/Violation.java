package cn.plutonight.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Violation对象", description="")
public class Violation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生Id")
    private Long studentId;

    @ApiModelProperty(value = "违规原因")
    private String reason;

    @ApiModelProperty(value = "座位Id")
    private Long seatId;

    @ApiModelProperty(value = "违规时间")
    private Timestamp createTime;
}
