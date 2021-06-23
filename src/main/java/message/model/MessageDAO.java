package message.model;


interface MessageDAO {
          int insert(MessageVO messageReport);
        //查找留言ID
          void findByMrepID(Integer mes_id);
        //更新檢舉狀態
          void updateMesStatus(MessageVO messageStatus);
       
}
