package com.spring.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateBossReq {
    @NotNull(message = "boss选择不能为空")
    private Integer bossId;

    private List<Goods> goodsList;

    @NotNull(message = "开盘者不能为空")
    private String bookmaker;
}
