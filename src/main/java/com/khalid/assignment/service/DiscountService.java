package com.khalid.assignment.service;

import com.khalid.assignment.dto.DiscountedAmountDto;
import com.khalid.assignment.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
public class DiscountService {

    @Autowired
    private UserService userService;

    private static final String EMPLOYEE_ROLE = "employee";
    private static final String AFFILIATE_ROLE = "affiliate";
    private static final double EMPLOYEE_PERCENTAGE = 0.3;
    private static final double AFFILIATE_PERCENTAGE = 0.1;
    private static final double TWO_YEARS_PERCENTAGE = 0.05;

    public DiscountedAmountDto applyDiscount(String userName, double amount, boolean isIncludeGroceries) {

        UserDto userDto = userService.findByUsername(userName);
        double calculatedAmount = calculateAll(userDto, amount, isIncludeGroceries);

        return DiscountedAmountDto.builder()
                .amount(BigDecimal.valueOf(amount))
                .discountedAmount(BigDecimal.valueOf(calculatedAmount))
                .build();

    }

    public double calculateAll(UserDto userDto, double amount, boolean isIncludeGroceries) {
        if(isIncludeGroceries)
            return amount;

        double amountAfterDiscount = calculate(amount, TWO_YEARS_PERCENTAGE, amount);

        if(userDto.getRole().equalsIgnoreCase(EMPLOYEE_ROLE)){
            amountAfterDiscount = calculate(amount, EMPLOYEE_PERCENTAGE, amountAfterDiscount);
        }
        if(userDto.getRole().equalsIgnoreCase(AFFILIATE_ROLE)){
            amountAfterDiscount = calculate(amount, AFFILIATE_PERCENTAGE, amountAfterDiscount);
        }
        if(userDto.getCreatedAt().isBefore(ZonedDateTime.now().minusYears(2).toInstant())){
            amountAfterDiscount = calculate(amount, TWO_YEARS_PERCENTAGE, amountAfterDiscount);
        }

        return amountAfterDiscount;
    }

    private double calculate(double amount, double discountPercentage, double amountAfterDiscount){
        return Double.min( amount - (amount * discountPercentage), amountAfterDiscount);
    }

}
