package com.khalid.assignment;

import com.khalid.assignment.bean.User;
import com.khalid.assignment.dto.UserDto;
import com.khalid.assignment.service.DiscountService;
import com.khalid.assignment.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.Instant;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountServiceUnitTest {

    @InjectMocks
    private DiscountService discountService;

    @Mock
    private UserService userService;

    @Test
    public void whenEmployeeUserAndIncludeGroceriesFalse() {
        when(userService.findByUsername("khalid")).thenReturn(new UserDto(new User(1, "khalid", "employee", Instant.now())));
        Assert.assertEquals(new BigDecimal("10.5"), discountService.applyDiscount("khalid", 15, false).getDiscountedAmount());
    }

    @Test
    public void whenEmployeeUserAndIncludeGroceriesTrue() {
        when(userService.findByUsername("khalid")).thenReturn(new UserDto(new User(1, "khalid", "employee", Instant.now())));
        Assert.assertEquals(new BigDecimal("15.0"), discountService.applyDiscount("khalid", 15, true).getDiscountedAmount());
    }

    @Test
    public void whenAffiliateUserAndIncludeGroceriesFalse() {
        when(userService.findByUsername("khalid")).thenReturn(new UserDto(new User(1, "khalid", "affiliate", Instant.now())));
        Assert.assertEquals(new BigDecimal("13.5"), discountService.applyDiscount("khalid", 15, false).getDiscountedAmount());
    }

    @Test
    public void whenAffiliateUserAndIncludeGroceriesTrue() {
        when(userService.findByUsername("khalid")).thenReturn(new UserDto(new User(1, "khalid", "affiliate", Instant.now())));
        Assert.assertEquals(new BigDecimal("15.0"), discountService.applyDiscount("khalid", 15, true).getDiscountedAmount());
    }

}