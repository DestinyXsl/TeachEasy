package com.homework.teach.service;

import com.homework.teach.domain.Student;
import com.homework.teach.domain.vo.HomeworkRecordVo;
import com.homework.teach.domain.vo.UpdateHomeworkRecordVo;
import org.springframework.stereotype.Service;
import com.homework.teach.domain.HomeworkRecord;

import java.util.List;
import java.util.Map;

@Service
public interface IHomeworkRecordService {
    public int insertHomeworkRecord(int homeworkId, List<Student> students, String tcode);

    public int updateHomeworkRecord(HomeworkRecordVo homeworkRecord);

    public int deleteHomeworkRecord(int id);

    public HomeworkRecord getHomeworkRecord(int id);

    public List<Map> getStudentHomeworkRecordList(int homeworkId);

    public HomeworkRecordVo getHomeworkRecordByTCode(String tcode);

    public HomeworkRecordVo getHomeworkRecordByHWIdAndTCode(String homeworkId,String tcode);

    public int updateHomeworkRecordStatus(UpdateHomeworkRecordVo vo);

    public int updateHomeworkRecordLevel(UpdateHomeworkRecordVo vo);
}