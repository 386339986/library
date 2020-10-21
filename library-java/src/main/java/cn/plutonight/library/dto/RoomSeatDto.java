package cn.plutonight.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoomSeatDto {

    @ApiModelProperty(value = "自习室座位图", required = true)
    private List<Integer[]> seats;

    @ApiModelProperty(value = "自习室大小", required = true)
    private String roomSize;

    @ApiModelProperty(value = "自习室座位编号", required = true)
    private String seatNumber;

}
