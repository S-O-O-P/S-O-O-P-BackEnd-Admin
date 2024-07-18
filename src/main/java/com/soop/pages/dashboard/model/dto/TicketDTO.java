package com.soop.pages.dashboard.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private int ticketCode;
    private int interestCode;
    private String title;
    private String content;
    private String region;
    private String poster;
    private String seller;
    private String sellerLink;
    private int regularPrice;
    private int discountPrice;
    private Date saleStartDate;
    private Date saleEndDate;
    private Date usageStartDate;
    private Date usageEndDate;
}
