package com.khalid.assignment.controller;

import com.khalid.assignment.dto.DiscountedAmountDto;
import com.khalid.assignment.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discounts")
public class DiscountsController {
    private final DiscountService discountService;

    public DiscountsController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<DiscountedAmountDto> getDiscountedBill(@RequestParam String userName, @RequestParam double amount, @RequestParam boolean isIncludeGroceries) {
        DiscountedAmountDto discountedAmountDto = discountService.applyDiscount(userName, amount, isIncludeGroceries);
        return new ResponseEntity<>(discountedAmountDto, HttpStatus.OK);
    }
}
