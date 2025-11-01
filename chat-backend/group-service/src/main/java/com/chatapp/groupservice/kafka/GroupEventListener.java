package com.chatapp.groupservice.kafka;

import com.chatapp.common.event.GroupCreatedEvent;
import com.chatapp.groupservice.model.Group;
import com.chatapp.groupservice.model.GroupMember;
import com.chatapp.groupservice.repository.GroupMemberRepository;
import com.chatapp.groupservice.repository.GroupRepository;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class GroupEventListener {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository memberRepository;

    // constructor
    public GroupEventListener(GroupRepository groupRepository, GroupMemberRepository memberRepository) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
    }

   @KafkaListener(topics = "group-created-topic", groupId = "group-service-group")
    public void createOneToOne(GroupCreatedEvent event) {
        System.out.println("📥 Received new group: " + event.getFromUserName() + " and " + event.getToUserName());

        if (event == null) return;
        groupRepository.findByRoomIdAndIsActiveTrue(event.getRoomId()).ifPresentOrElse(
                g -> {
                    System.out.println("Group already exists: " + event.getRoomId());
                },
                () -> {
                    Group group = new Group();
                    group.setRoomId(event.getRoomId());
                    group.setName(event.getFromUserName() + " & " + event.getToUserName());
                    group.setOwnerId(event.getAccepterId());
                    group.setAvatar("");
                    group.setDescription("One-to-one chat between " + event.getFromUserName() + " and " + event.getToUserName());
                    group.setRoomType("one-to-one");
                    group.setCreatedAt(Instant.now());
                    group.setActive(true);
                    groupRepository.save(group);
                    addMember(event.getRoomId(), event.getAccepterId(), "member");
                    addMember(event.getRoomId(), event.getRequesterId(), "member");
                }
        );
    }

    private void addMember(String roomId, String userId, String role) {
        GroupMember member = new GroupMember();
        member.setMembershipId("M" + UUID.randomUUID().toString().substring(0, 8));
        member.setUserId(userId);
        member.setRole(role);
        member.setJoinedAt(Instant.now());
        member.setRoomId(roomId);
        member.setActive(true);
        memberRepository.save(member);
    }


}
