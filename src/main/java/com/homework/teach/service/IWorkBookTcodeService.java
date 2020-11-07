package com.homework.teach.service;

import org.springframework.stereotype.Service;
import com.homework.teach.domain.WorkBookTcode;

@Service
public interface IWorkBookTcodeService {
    public int insertWorkBookTcode(WorkBookTcode workBookTcode);

    public int updateWorkBookTcode(WorkBookTcode workBookTcode);

    public int deleteWorkBookTcode(int id);

    public WorkBookTcode getWorkBookTcode(int id);
}