package com.homework.teach.service.impl;

import com.homework.teach.domain.HomeworkRecord;
import com.homework.teach.domain.Student;
import com.homework.teach.domain.vo.HomeworkRecordVo;
import com.homework.teach.domain.vo.UpdateHomeworkRecordVo;
import com.homework.teach.mapper.HomeworkRecordMapper;
import com.homework.teach.service.IHomeworkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeworkRecordServiceImpl implements IHomeworkRecordService {
    @Autowired
    private HomeworkRecordMapper homeworkRecordMapper;


    @Override
    public int insertHomeworkRecord(int homeworkId, List<Student> students, String tcode) {
        return homeworkRecordMapper.insertHomeworkRecord(homeworkId,students,tcode);
    }

    @Override
    public int updateHomeworkRecord(HomeworkRecordVo homeworkRecord) {
        return homeworkRecordMapper.updateHomeworkRecord(homeworkRecord);
    }

    @Override
    public int deleteHomeworkRecord(int id) {
        return homeworkRecordMapper.deleteHomeworkRecord(id);
    }

    @Override
    public HomeworkRecord getHomeworkRecord(int id) {
        return homeworkRecordMapper.getHomeworkRecord(id);
    }

    @Override
    public List<Map> getStudentHomeworkRecordList(int homeworkId) {
        return homeworkRecordMapper.getStudentHomeworkRecordList(homeworkId);
    }

    @Override
    public HomeworkRecordVo getHomeworkRecordByTCode(String tcode) {
        return homeworkRecordMapper.getHomeworkRecordByTCode(tcode);
    }

    @Override
    public HomeworkRecordVo getHomeworkRecordByHWIdAndTCode(String homeworkId, String tcode) {
        return homeworkRecordMapper.getHomeworkRecordByHWIdAndTCode(homeworkId,tcode);
    }

    @Override
    public int updateHomeworkRecordStatus(UpdateHomeworkRecordVo vo) {
        return homeworkRecordMapper.updateHomeworkRecordStatus(vo);
    }

    @Override
    public int updateHomeworkRecordLevel(UpdateHomeworkRecordVo vo) {
        return homeworkRecordMapper.updateHomeworkRecordLevel(vo);
    }
}