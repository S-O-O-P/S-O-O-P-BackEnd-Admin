package com.soop.pages.notice.model.service;

import com.soop.pages.notice.model.dao.NoticeMapper;
import com.soop.pages.notice.model.dto.NoticeFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void registNotice(NoticeFileDTO noticeFileDTO) {

        noticeMapper.registNotice(noticeFileDTO);
    }
}
