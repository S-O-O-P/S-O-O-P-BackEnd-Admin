package com.soop.pages.notice.model.dao;

import com.soop.pages.notice.model.dto.NoticeFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    void registNotice(NoticeFileDTO noticeFileDTO);
}
