package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> finAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
      return   labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象 也就是把条件封装到哪个对象里 where 类名=label.getId
             * @param criteriaQuery 封装的都是查询关键字 order by group by等等
             * @param criteriaBuilder  用来封装条件对象的
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //定义一个集合来存放所有条件
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname() == null && "".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where labelname like "%小明%"
                    list.add(predicate);
                }
                if (label.getState() == null && "".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),  label.getState() );//where state = 1
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return criteriaBuilder.and(parr);
            }

        });
    }
}
