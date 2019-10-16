package com.synway.blogserver.service;

import com.synway.blogserver.dao.BlogDao;
import com.synway.blogserver.domain.Blog;
import com.synway.blogserver.domain.Type;
import com.synway.blogserver.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;

    public Blog getBlog(Long id){
        return blogDao.findById(id).get();
    }

    public Page<Blog> listBlog(Pageable pageable,Blog blog){
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(cb.like(root.<String>get("title"),"%" + blog.getTitle()));
                }
                if(blog.getType().getId() != null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getType().getId()));
                }
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    public Blog saveBlog(Blog blog){
        return blogDao.save(blog);
    }

    public Blog updateBlog(Long id,Blog blog){
        Blog b = blogDao.findById(id).get();
        if(b == null){
            throw new NotFoundException("博客不存在");
        }
        BeanUtils.copyProperties(b,blog);
        blogDao.save(b);
        return null;
    }

    public void deleteBlog(Long id){
        blogDao.deleteById(id);
    }
}
