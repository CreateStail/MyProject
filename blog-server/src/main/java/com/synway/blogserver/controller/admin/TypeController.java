package com.synway.blogserver.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.synway.blogserver.domain.Type;
import com.synway.blogserver.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;
    private  static Gson gson = new Gson();

    @RequestMapping("/types")
    public String types(@RequestParam int pageSize,
                        @RequestParam int page){
/*        Sort sort = new Sort("id");
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1,Integer.parseInt(pageSize),sort);
        Page<Type> types = typeService.listType(pageable);
        Map<String,Object> resultMap = new HashMap<>();
        List<Type> typesList = types.getContent();
        resultMap.put("total",types.getTotalElements());
        resultMap.put("rows",typesList);
        return gson.toJson(resultMap);*/

        PageHelper.startPage(page,pageSize);
        List<Type> list =typeService.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        Map<String,Object> result = new HashMap<>();
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return gson.toJson(result);
    }

    @PostMapping("/save_type")
    public JsonData save_type(@RequestBody Type type){
        /*//查询分类是否已保存过
        boolean flag = typeService.typeIsExist(type.getName());
        if(!flag){
            Type t = typeService.saveType(type);
            if(t != null){
                return JsonData.buildSuccess("保存分类成功");
            }else{
                return JsonData.buildError("保存分类失败");
            }
        }else{
            return JsonData.buildError("分类名称已存在");
        }*/
        boolean flag = typeService.typeIsExist(type.getName());
        if(!flag){
            int saveCount = typeService.saveType(type);
            if(saveCount > 0){
                return JsonData.buildSuccess("保存分类成功");
            }else{
                return JsonData.buildError("保存分类失败");
            }
        }else{
            return JsonData.buildError("分类名称已存在");
        }
    }

    @GetMapping("/edit_type")
    public JsonData edit_type(@RequestParam String id){
        Type type = typeService.getType(Long.parseLong(id));
        return JsonData.buildSuccess(type);
    }

    @PostMapping("/update_type")
    public JsonData update_type(@RequestBody Type type){
        int updateCount = typeService.update(type);
        if(updateCount > 0){
            return  JsonData.buildSuccess("修改分类成功");
        }else{
            return JsonData.buildError("修改分类失败");
        }
    }

    @GetMapping("/delete_type")
    public JsonData delete_type(@RequestParam String id){
        typeService.delete(Integer.parseInt(id));
        return JsonData.buildSuccess("删除成功");
    }


}
