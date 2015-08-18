package com.sourabh.businessService;

import com.sourabh.entity.Message;

import java.util.ArrayList;

public interface IMessageService {

    ArrayList<Message> fetchMessages(String location);
}
