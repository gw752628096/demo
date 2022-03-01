package com.spring.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmReq {
    private Long userId;

    @NotNull(message = "boss选择不能为空")
    private Integer chooseBoss;

    private Goods one;

    private Goods two;

    private Goods three;

    @NotNull(message = "开盘者不能为空")
    private String bookmaker;

    @NotNull(message = "活动id不能为空")
    private String activityId;

    private Long orderUserId;
}
