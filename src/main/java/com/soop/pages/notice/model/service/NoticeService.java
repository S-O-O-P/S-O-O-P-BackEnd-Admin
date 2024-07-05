package com.soop.pages.notice.model.service;

import com.soop.pages.notice.model.dao.NoticeMapper;
import com.soop.pages.notice.model.dto.NoticeMemberFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void registNotice(NoticeMemberFileDTO noticeMemberFileDTO) {

        noticeMapper.registNotice(noticeMemberFileDTO);
    }

    public List<NoticeMemberFileDTO> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    public NoticeMemberFileDTO noticeDetail(String id) {

        return noticeMapper.noticeDetail(id);
    }
}
