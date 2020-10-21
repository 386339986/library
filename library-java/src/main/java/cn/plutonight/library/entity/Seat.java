package cn.plutonight.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;

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
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Seat对象", description="")
public class Seat implements Serializable {

    public interface STATUS {
        /**
         * 离开
         */
        Integer OUT = 1;

        /**
         * 预约
         */
        Integer ORDER = 2;

        /**
         * 在座
         */
        Integer IN = 3;

        /**
         * 暂离
         */
        Integer TEMP = 4;

        /**
         * 异常
         */
        Integer ERR = 5;
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "座位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生id")
    private Long studentId;

    @ApiModelProperty(value = "学校id")
    private Long schoolId;

    @ApiModelProperty(value = "自习室id")
    private Long roomId;

    @ApiModelProperty(value = "座位名称")
    private String name;

    @ApiModelProperty(value = "座位行")
    private Integer seatRow;

    @ApiModelProperty(value = "座位列")
    private Integer seatCol;

    @ApiModelProperty(value = "状态：1 空闲 2 预约 3 正常使用 4 暂离")
    private Integer status;

    @ApiModelProperty(value = "座位预约时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "入座时间")
    private Timestamp useTime;

    @ApiModelProperty(value = "座位释放时间")
    private Timestamp endTime;
}
