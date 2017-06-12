package com.skiday.app.skiday.dao;

import com.skiday.app.skiday.dto.SocialMediaPostDTO;

import java.util.List;

public interface ISocialMediaDAO {
    void addSocialMediaPost(SocialMediaPostDTO socialMediaPostDTO);
    List<SocialMediaPostDTO> getAllSocialMediaPosts();
}
