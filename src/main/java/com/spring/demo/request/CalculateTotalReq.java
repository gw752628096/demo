package com.spring.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateTotalReq {
    @NotNull(message = "开盘者不能为空")
    private String bookmaker;
}
