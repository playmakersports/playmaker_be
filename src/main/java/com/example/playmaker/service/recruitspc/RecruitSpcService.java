package com.example.playmaker.service.recruitspc;

import com.example.playmaker.web.recruitspc.dto.RecruitSpcForm;
import com.example.playmaker.web.recruitspc.dto.RecruitSpcInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecruitSpcService {
    void insertRecruitSpc(RecruitSpcForm recruitSpcForm);
    List<RecruitSpcInfo> selectAll();
    void updateRecruitSpc(Long id, RecruitSpcForm recruitSpcForm);

}
