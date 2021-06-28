package com.message.model;


interface MessageDAO {
          int insert(MessageVO messageReport);
       
          void findByMrepID(Integer mes_id);
       
          void updateMesStatus(MessageVO messageStatus);
       
}
