package message.model;


interface MessageDAO {
          int insert(MessageVO messageReport);
        //�d��d��ID
          void findByMrepID(Integer mes_id);
        //��s���|���A
          void updateMesStatus(MessageVO messageStatus);
       
}
