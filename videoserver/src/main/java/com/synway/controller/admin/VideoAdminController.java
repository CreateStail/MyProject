package com.synway.controller.admin;

import com.synway.domain.Video;
import com.synway.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/api/v1/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    /**
     * 根据ID删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping("del_by_id")
    public Object delById(@RequestParam(value = "video_id",required = true)int videoId){
        return videoService.delete(videoId);
    }

    /**
     * 根据ID更新视频
     * @return
     */
    @PutMapping("update_by_id")
    public Object update(@RequestBody Video video){
        return videoService.update(video);
    }

    /**
     * 保存视频对象
     * @return
     */
    @PostMapping("/save")
    public Object save(@RequestBody Video video){
        return videoService.save(video);
    }

}
