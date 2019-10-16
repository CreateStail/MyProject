package com.synway.blogserver.controller.admin;

import com.google.gson.Gson;
import com.synway.blogserver.domain.Blog;
import com.synway.blogserver.service.BlogService;
import com.synway.blogserver.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String blogs(@RequestParam String pageSize,
                          @RequestParam String page){
        Sort sort = new Sort("id");
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1,Integer.parseInt(pageSize),sort);
        Page<Blog> blogs = blogService.listBlog(pageable,new Blog());
        Map<String,Object> resultMap = new HashMap<>();
        List<Blog> blogList = blogs.getContent();
        resultMap.put("total",blogs.getTotalElements());
        resultMap.put("rows",blogList);
        return gson.toJson(resultMap);
    }

}
