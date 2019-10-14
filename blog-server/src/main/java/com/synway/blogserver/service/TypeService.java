package com.synway.blogserver.service;

import com.synway.blogserver.dao.TypeDao;
import com.synway.blogserver.dao.mapper.TypeMapper;
import com.synway.blogserver.domain.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TypeService {

/*    @Autowired
    private TypeDao typeDao;

    @Transactional
    public Type saveType(Type type){
        return typeDao.save(type);
    }

    public Type getType(Long id){
        return typeDao.findById(id).get();
    }

    public Page<Type> listType(Pageable pageable){
        return typeDao.findAll(pageable);
    }


    @Transactional
    public Type updateType(Long id,Type type){
        Type t = typeDao.findById(id).get();
        BeanUtils.copyProperties(type,t);
        return  typeDao.save(t);
    }

    @Transactional
    public void deleteType(Long id){
        typeDao.deleteById(id);
    }

    public boolean typeIsExist(String typeName){
        int count = typeDao.findTypeByName(typeName);
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }*/

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    public int saveType(Type type) {
        return typeMapper.save_type(type);
    }

    public Type getType(Long id) {
        return typeMapper.findById(id);
    }

    public List<Type> listType() {
        return typeMapper.findAll();
    }

    @Transactional
    public int update(Type type) {
        return typeMapper.update(type);
    }

    @Transactional
    public int delete(int id) {
        return typeMapper.delete(id);
    }

    public boolean typeIsExist(String typeName) {
        int count = typeMapper.findTypeByName(typeName);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
