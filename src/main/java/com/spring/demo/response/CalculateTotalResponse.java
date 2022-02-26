package com.spring.demo.response;

import com.spring.demo.vo.TotalBetRecordVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateTotalResponse {
    private int code;
    private String message;
    private List<TotalBetRecordVo> totalBetRecordVoList;
}
