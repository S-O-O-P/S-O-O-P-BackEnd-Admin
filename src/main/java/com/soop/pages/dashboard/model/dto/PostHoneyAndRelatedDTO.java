package com.soop.pages.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostHoneyAndRelatedDTO {
    private int honeyId;
    private String honeyTitle;
    private Date honeyAt;
    private int honeyReportCount;
    private MemberDTO member;
    private InterestDTO interest;
    private TicketDTO ticket;
}
