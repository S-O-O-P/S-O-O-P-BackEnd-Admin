package com.soop.pages.notice.model.service;

import com.soop.pages.notice.model.dao.NoticeMapper;
import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeMemberFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    private FileDTO fileDTO;

    public void registNotice(NoticeMemberFileDTO noticeMemberFileDTO) {

        noticeMapper.registNotice(noticeMemberFileDTO);

        noticeMemberFileDTO.setNoticeCode(noticeMemberFileDTO.getNoticeCode());

        System.out.println(" service 쪽");

    }

    public List<NoticeMemberFileDTO> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    public NoticeMemberFileDTO noticeDetail(String id) {

        return noticeMapper.noticeDetail(id);
    }

    public void editNotice(NoticeMemberFileDTO noticeMemberFileDTO) {

        noticeMapper.editNotice(noticeMemberFileDTO);
    }

    public void deleteNotice(int id) {
        noticeMapper.deleteNotice(id);
    }

    public void registNoticeFile(FileDTO fileDTO) {

        noticeMapper.registNoticeFile(fileDTO);
    }

}
