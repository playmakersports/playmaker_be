package com.example.playmaker.service.mercenaryrecruitspc;

import com.example.playmaker.domain.mercenaryrecruitspc.MercenaryRecruitSpc;
import com.example.playmaker.web.mercenaryrecruitspc.dto.MercenaryRecruitSpcForm;
import com.example.playmaker.web.mercenaryrecruitspc.dto.MercenaryRecruitSpcInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MercenaryRecruitSpcService {
    List<MercenaryRecruitSpcInfo> selectAll();
    void insertMercenaryRecruitSpc(MercenaryRecruitSpcForm mercenaryRecruitSpcForm);
    void updateMercenaryRecruitSpc(Long id, MercenaryRecruitSpcForm mercenaryRecruitSpcForm);
}
