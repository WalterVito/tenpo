package com.tenpo.challenge.controller;

import com.tenpo.challenge.model.Log;
import com.tenpo.challenge.service.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class HistoryControllerTest {
    @Mock
    private LogService logService;

    @InjectMocks
    private HistoryController historyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTutorialsPage() {

        List<Log> logList = new ArrayList<>();
        logList.add(new Log("history/log","","{result:1}","?size=100"));
        Page<Log> mockPageLog = new PageImpl<Log>(logList);
        when(logService.FindAndSortByPathLogs(anyString(), anyInt(), anyInt(), any())).thenReturn(mockPageLog);


        ResponseEntity<Map<String, Object>> response = historyController.getAllTutorialsPage("test", 0, 3, new String[]{"id,desc"});

        verify(logService, times(1)).FindAndSortByPathLogs("test", 0, 3, new String[]{"id,desc"});
        assertEquals(HttpStatus.OK, ((ResponseEntity<?>) response).getStatusCode());
        var logs = (List<Log>)response.getBody().get("logs");
        assertEquals(logs.get(0).getEndpoint(), logList.get(0).getEndpoint());
        assertEquals(logs.get(0).getRequest(), logList.get(0).getRequest());
        assertEquals(logs.get(0).getResponse(), logList.get(0).getResponse());
        assertEquals(logs.get(0).getQueryString(), logList.get(0).getQueryString());
        assertEquals(logs.size(), logList.size());
    }
}