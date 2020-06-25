package com.khalid.assignment.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DiscountedAmountDto {
    private BigDecimal discountedAmount;
    private BigDecimal amount;
}
