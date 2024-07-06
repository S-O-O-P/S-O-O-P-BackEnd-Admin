package com.soop.pages.notice.model.dao;

import com.soop.pages.notice.model.dto.NoticeMemberFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    void registNotice(NoticeMemberFileDTO noticeMemberFileDTO);

    List<NoticeMemberFileDTO> getNoticeList();

    NoticeMemberFileDTO noticeDetail(String id);

    void editNotice(NoticeMemberFileDTO noticeMemberFileDTO);

    void deleteNotice(int id);
}
