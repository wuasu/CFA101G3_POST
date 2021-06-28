package com.category.model;

import java.util.*;

interface CategoryDAO {
          void insert(CategoryVO category);
          void update(CategoryVO category);
          void delete(Integer cat_id);
          CategoryVO findByCatId(Integer cat_id);       
          List<CategoryVO> getAll();       
}