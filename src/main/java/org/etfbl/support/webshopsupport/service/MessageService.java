package org.etfbl.support.webshopsupport.service;

import org.etfbl.support.webshopsupport.dao.DAOUtil;
import org.etfbl.support.webshopsupport.dao.MessageDao;
import org.etfbl.support.webshopsupport.dto.Message;

import java.util.List;

public class MessageService {

    public List<Message> findAll() {
        return MessageDao.findAll();
    }

    public Message openMessage(Long id) {
        Message message = MessageDao.findById(id);
        if(message!=null && !message.getRead()){
            MessageDao.readMessage(id);
        }
        return message;
    }
}
