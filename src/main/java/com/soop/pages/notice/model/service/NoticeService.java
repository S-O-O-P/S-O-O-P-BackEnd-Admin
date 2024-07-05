package com.soop.pages.notice.model.service;

import com.soop.pages.notice.model.dao.NoticeMapper;
import com.soop.pages.notice.model.dto.NoticeFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void registNotice(NoticeFileDTO noticeFileDTO) {

        noticeMapper.registNotice(noticeFileDTO);
    }

    public List<NoticeFileDTO> getNoticeList() {
        return noticeMapper.getNoticeList();
    }
}
