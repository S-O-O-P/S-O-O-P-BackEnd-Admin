package com.soop.pages.notice.model.dao;

import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    void registNotice(NoticeDTO noticeMemberFileDTO);

    List<NoticeDTO> getNoticeList();

    NoticeDTO noticeDetail(int id);

    void editNotice(NoticeDTO noticeDTO);

    void registNoticeFile(FileDTO fileDTO);

    FileDTO noticeDetailFile(int id);

    void deleteNotice(int id);
    void deleteNoticeFile(int id);

    void editNoticeFile(FileDTO fileDTO);

}
