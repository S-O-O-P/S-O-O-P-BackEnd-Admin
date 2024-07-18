package com.soop.pages.honeypot.model.service;


import com.soop.pages.honeypot.model.dao.HoneypotMapper;
import com.soop.pages.honeypot.model.dto.HoneypotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoneypotService {

    @Autowired
    private HoneypotMapper honeypotMapper;

    public List<HoneypotDTO> getHoneypotList() {
        List<HoneypotDTO> honeypotList = honeypotMapper.getHoneypotList();

        // Assign display order sequentially
        int displayOrder = honeypotList.size();
        for (HoneypotDTO honeypot : honeypotList) {
            honeypot.setDisplayOrder(displayOrder--);
        }

        return honeypotList;
    }

    public HoneypotDTO getHoneypotByHoneypotCode(int honeypotCode) {



        return honeypotMapper.getHoneypotByHoneypotCode(honeypotCode);
    }

    public void updateHoneypotStatus(int honeypotCode, String newStatus) {
        honeypotMapper.updateHoneypotStatus(honeypotCode, newStatus);
    }
}
