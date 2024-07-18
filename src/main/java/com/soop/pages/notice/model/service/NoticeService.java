package com.soop.pages.notice.model.service;

import com.soop.pages.notice.model.dao.NoticeMapper;
import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
    private FileDTO fileDTO;

    public void registNotice(NoticeDTO noticeMemberFileDTO) {

        noticeMapper.registNotice(noticeMemberFileDTO);

        noticeMemberFileDTO.setNoticeCode(noticeMemberFileDTO.getNoticeCode());

    }

    public List<NoticeDTO> getNoticeList() {
        List<NoticeDTO> noticeList = noticeMapper.getNoticeList();

        // Assign display order
        int displayOrder = noticeList.size();
        for (NoticeDTO notice : noticeList) {
            notice.setDisplayOrder(displayOrder--);
        }

        return noticeList;
    }

    public NoticeDTO noticeDetail(int id) {

        return noticeMapper.noticeDetail(id);
    }

    public FileDTO noticeDetailFile(int id) {

        return noticeMapper.noticeDetailFile(id);
    }

    public void editNotice(NoticeDTO noticeMemberFileDTO) {

        noticeMapper.editNotice(noticeMemberFileDTO);
    }

    public void registNoticeFile(FileDTO fileDTO) {

        noticeMapper.registNoticeFile(fileDTO);
    }

    public void deleteNotice(int id) {

        noticeMapper.deleteNoticeFile(id);

        noticeMapper.deleteNotice(id);

    }

    public void editNoticeFile(FileDTO fileDTO) {

        noticeMapper.editNoticeFile(fileDTO);
    }

    public void deleteNoticeFile(int id) {
        noticeMapper.deleteNoticeFile(id);
    }
}
