package com.soop.pages.notice.model.dao;

import com.soop.pages.notice.model.dto.NoticeFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    void registNotice(NoticeFileDTO noticeFileDTO);

    List<NoticeFileDTO> getNoticeList();
}
