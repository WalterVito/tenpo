package com.tenpo.challenge.service.impl;


import com.tenpo.challenge.model.Log;
import com.tenpo.challenge.repository.LogRepository;
import com.tenpo.challenge.service.LogService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public Page<Log> FindAndSortByPathLogs(String path, int page, int size, String[] sort) {
        List<Order> orders = new ArrayList<Order>();

        if (sort[0].contains(",")) {

            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }

        List<Log> logs = new ArrayList<Log>();
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<Log> pageLogs;
        if (path == null)
            pageLogs = logRepository.findAll(pagingSort);
        else
            pageLogs = logRepository.findByEndpointContaining(path, pagingSort);

        return pageLogs;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}


