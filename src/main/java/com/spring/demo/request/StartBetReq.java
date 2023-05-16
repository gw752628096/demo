package com.spring.demo.request;

import com.spring.demo.vo.BetConfigVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartBetReq {
    @NotNull(message = "最大单柱金额不能为空")
    private String maxMoney;

    @NotNull(message = "副本id不能为空")
    private Long dungeonsId;

    private List<BetConfigVo> betConfigList;
}
