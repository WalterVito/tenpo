package com.tenpo.challenge.service;

import com.tenpo.challenge.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogService {

    Page<Log> FindAndSortByPathLogs(String path,int page,int size,String[] sort);
}
