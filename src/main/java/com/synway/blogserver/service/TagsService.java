package com.synway.blogserver.service;

import com.synway.blogserver.dao.TagsDao;
import com.synway.blogserver.dao.mapper.TagsMapper;
import com.synway.blogserver.domain.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagsService {
/*    @Autowired
    private TagsDao tagsDao;

    @Transactional
    public Tag saveTag(Tag tag){
        return tagsDao.save(tag);
    }

    public Tag getTag(Long id){
        return tagsDao.findById(id).get();
    }

    public Page<Tag> listTag(Pageable pageable){
        return tagsDao.findAll(pageable);
    }


    @Transactional
    public Tag updateTag(Long id,Tag tag){
        Tag t = tagsDao.findById(id).get();
        BeanUtils.copyProperties(tag,t);
        return  tagsDao.save(t);
    }

    @Transactional
    public void deleteTag(Long id){
        tagsDao.deleteById(id);
    }

    public boolean tagIsExist(String tagName){
        int count = tagsDao.findTagByName(tagName);
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }*/

    @Autowired
    private TagsMapper tagsMapper;

    @Transactional
    public int saveTag(Tag tag) {
        return tagsMapper.save_tag(tag);
    }

    public Tag getTag(Long id) {
        return tagsMapper.findById(id);
    }

    public List<Tag> listtag() {
        return tagsMapper.findAll();
    }

    @Transactional
    public int update(Tag tag) {
        return tagsMapper.update(tag);
    }

    @Transactional
    public int delete(int id) {
        return tagsMapper.delete(id);
    }

    public boolean tagIsExist(String tagName) {
        int count = tagsMapper.findtagByName(tagName);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
