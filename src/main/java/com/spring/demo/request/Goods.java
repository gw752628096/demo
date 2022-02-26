package com.spring.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    @NotNull(message = "物品不能为空")
    private Integer id;

    @NotNull(message = "金额不能为空")
    private Integer money;
}
