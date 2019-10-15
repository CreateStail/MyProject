package com.synway.blogserver.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.synway.blogserver.domain.Blog;
import com.synway.blogserver.service.BlogService;
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
public class BlogsController {

    @Autowired
    private BlogService blogService;

    private final static Gson gson = new Gson();

    @GetMapping("/blogs")
    public String blogs(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam String keyMap){
        Map<String,Object> params = gson.fromJson(keyMap,Map.class);
        PageHelper.startPage(page,pageSize);
        List<Blog> list = blogService.listBlogByCondition(params);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        Map<String,Object> result = new HashMap<>();
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return gson.toJson(result);
    }

}
