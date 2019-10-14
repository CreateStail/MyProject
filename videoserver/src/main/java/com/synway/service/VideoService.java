package com.synway.service;

import com.synway.dao.VideoMapper;
import com.synway.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public List<Video> findAll(){
        return videoMapper.findAll();
    }

    public Video findBuId(int id){
        return videoMapper.findById(id);
    }

    public int update(Video video){
        int result = videoMapper.update(video);
        return result;
    }

    public int delete(int id){
        int result = videoMapper.delete(id);
        return result;
    }

    public int save(Video video){
        int id = videoMapper.save(video);
        System.out.println("新增id为:" + video.getId());
        return id;
    }
}
