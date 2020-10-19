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
 * 学校信息
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="School对象", description="学校信息")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学校id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学校名称")
    private String name;

    @ApiModelProperty(value = "学校所在省份")
    private String province;

    @ApiModelProperty(value = "学校所在市")
    private String city;

    @ApiModelProperty(value = "状态：1 启用 2 停用")
    private Integer status;


}
