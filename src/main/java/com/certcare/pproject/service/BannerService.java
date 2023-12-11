package com.certcare.pproject.service;

import com.certcare.pproject.domain.Banner;
import com.certcare.pproject.dto.BannerDto;
import com.certcare.pproject.repository.BannerRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BannerService {
    private final BannerRepository bannerRepository;

//    public List<Banner> getBannerInfo() {
//        List<Banner> banners = bannerRepository.findAll();
//    }
}
