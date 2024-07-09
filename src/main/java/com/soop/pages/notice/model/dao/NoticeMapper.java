package com.soop.pages.notice.model.dao;

import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeMemberFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeMemberFileDTO> getNoticeList();

    void registNotice(NoticeMemberFileDTO noticeMemberFileDTO);

    void registNoticeFile(FileDTO fileDTO);

    NoticeMemberFileDTO noticeDetail(int id);

    void editNotice(NoticeMemberFileDTO noticeMemberFileDTO);

    void deleteNotice(int id);

    void deleteNoticeFile(int id);
}
