package message_report.model;


interface MessageReportDAO {
          int insert(MessageReportVO messageReport);
        //查找檢舉ID
          void findByMrepID(Integer mrep_id); 
        //更新檢舉狀態
          void updateMrepStatus(MessageReportVO messageReportStatus);
       
}
