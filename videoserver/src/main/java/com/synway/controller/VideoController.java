package com.synway.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.synway.domain.Video;
import com.synway.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    private final static Logger appLog = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private VideoService videoService;

    /**
     * 分页接口
     * @param page 当前第几页，默认第一页
     * @param size 每页显示几条,默认十条
     * @return
     */
    @RequestMapping("/page")
    public Object pageVideo(@RequestParam(value = "page",defaultValue = "1")int page,
                            @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<Video> list = videoService.findAll();
        PageInfo<Video> pageInfo = new PageInfo<Video>(list);
        Map<String,Object> data = new HashMap<>();
        data.put("total_size",pageInfo.getTotal());//总条数
        data.put("total_page",pageInfo.getPages());//总页数
        data.put("current_page",page);//当前页
        data.put("data",pageInfo.getList());//数据
        return data;
    }

    /**
     * 根据ID找视频
     * @param videoId
     * @return
     */
    @GetMapping("find_by_id")
    public Object findBuId(@RequestParam(value = "video_id",required = true)int videoId){
        appLog.info("module=video,api=find_by_id,video_id={}",videoId);
        return videoService.findBuId(videoId);
    }

/*    *//**
     * 根据ID删除视频
     * @param videoId
     * @return
     *//*
    @DeleteMapping("del_by_id")
    public Object delById(@RequestParam(value = "video_id",required = true)int videoId){
        return videoService.delete(videoId);
    }

    *//**
     * 根据ID更新视频
     * @return
     *//*
    @PutMapping("update_by_id")
    public Object update(@RequestBody Video video){
        return videoService.update(video);
    }

    *//**
     * 保存视频对象
     * @return
     *//*
    @PostMapping("/save")
    public Object save(@RequestBody Video video){
        return videoService.save(video);
    }*/

}
