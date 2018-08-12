package cn.iruier.service.impl;

import cn.iruier.dao.PaperDetailMapper;
import cn.iruier.entity.PaperDetail;
import cn.iruier.service.PaperDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperDetailServiceImpl implements PaperDetailService {

    @Autowired
    private PaperDetailMapper mapper;

    @Override
    public void save(PaperDetail paperDetail) {
        mapper.save(paperDetail);
    }
}
