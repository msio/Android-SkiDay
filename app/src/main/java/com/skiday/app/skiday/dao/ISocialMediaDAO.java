package com.skiday.app.skiday.dao;

import com.skiday.app.skiday.dto.SocialMediaPostDTO;

import java.io.Serializable;
import java.util.List;

public interface ISocialMediaDAO extends Serializable{
    void addSocialMediaPost(SocialMediaPostDTO socialMediaPostDTO);
    List<SocialMediaPostDTO> getAllSocialMediaPosts();
}
