package category.model;

import java.util.*;

interface CategoryDAO {
          int insert(CategoryVO category);
          void update(CategoryVO category);
          int delete(Integer cat_id);
          CategoryVO findByCatId(Integer cat_id);       
          List<CategoryVO> getAll();       
}