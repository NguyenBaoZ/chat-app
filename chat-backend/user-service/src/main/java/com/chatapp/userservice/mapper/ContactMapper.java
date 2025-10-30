package com.chatapp.userservice.mapper;

import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactDto toDto(Contact contact) {
        if (contact == null) return null;
        return new ContactDto(
                contact.getContactId(),
                contact.getOwnerId(),
                contact.getFriendId(),
                contact.getNickname(),
                contact.getStatus(),
                contact.isActive()
        );
    }

    public Contact toEntity(ContactDto dto) {
        if (dto == null) return null;
        Contact contact = new Contact();
        contact.setContactId(dto.getContactId());
        contact.setOwnerId(dto.getOwnerId());
        contact.setFriendId(dto.getFriendId());
        contact.setNickname(dto.getNickname());
        contact.setStatus(dto.getStatus());
        contact.setActive(dto.isActive());
        return contact;
    }
}
