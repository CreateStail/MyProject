package com.synway.blogserver.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.synway.blogserver.domain.Tag;
import com.synway.blogserver.service.TagsService;
import com.synway.blogserver.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagsService tagsService;

    private final static Gson gson = new Gson();

    @RequestMapping("/tags")
    public String tags(@RequestParam int pageSize,
                        @RequestParam int page){
/*        Sort sort = new Sort("id");
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1,Integer.parseInt(pageSize),sort);
        Page<Tag> tags = tagsService.listTag(pageable);
        Map<String,Object> resultMap = new HashMap<>();
        List<Tag> tagsList = tags.getContent();
        resultMap.put("total",tags.getTotalElements());
        resultMap.put("rows",tagsList);
        return gson.toJson(resultMap);*/
        PageHelper.startPage(page,pageSize);
        List<Tag> list = tagsService.listtag();
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        Map<String,Object> result = new HashMap<>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return gson.toJson(result);
    }

    @PostMapping("/save_tag")
    public JsonData save_tag(@RequestBody Tag tag){
        //查询分类是否已保存过
        boolean flag = tagsService.tagIsExist((tag.getName()));
        if(!flag){
            int saveCount = tagsService.saveTag(tag);
            if(saveCount >0){
                return JsonData.buildSuccess("保存标签成功");
            }else{
                return JsonData.buildError("保存标签失败");
            }
        }else{
            return JsonData.buildError("标签名称已存在");
        }
    }

    @GetMapping("/edit_tag")
    public JsonData edit_tag(@RequestParam String id){
        Tag tag = tagsService.getTag(Long.parseLong(id));
        return JsonData.buildSuccess(tag);
    }

    @PostMapping("/update_tag")
    public JsonData update_tag(@RequestBody Tag tag){
        int updateCount = tagsService.update(tag);
        if(updateCount > 0){
            return  JsonData.buildSuccess("修改标签成功");
        }else{
            return JsonData.buildError("修改标签失败");
        }
    }

    @GetMapping("/delete_tag")
    public JsonData delete_tag(@RequestParam String id){
        tagsService.delete(Integer.parseInt(id));
        return JsonData.buildSuccess("删除成功");
    }

}
