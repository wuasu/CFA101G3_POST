package message_report.model;


interface MessageReportDAO {
          int insert(MessageReportVO messageReport);
        //�d�����|ID
          void findByMrepID(Integer mrep_id); 
        //��s���|���A
          void updateMrepStatus(MessageReportVO messageReportStatus);
       
}
