package com.chatapp.groupservice.service;

import com.chatapp.groupservice.dto.*;
import com.chatapp.groupservice.model.*;
import com.chatapp.groupservice.repository.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository memberRepository;
    private final GroupInvitationRepository invitationRepository;

    // constructor
    public GroupService(GroupRepository groupRepository, GroupMemberRepository memberRepository, GroupInvitationRepository invitationRepository) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.invitationRepository = invitationRepository;
    }

    private String getCurrentUserId(String userId) {
        if (userId == null) throw new RuntimeException("Missing X-User-Id");
        System.out.println("Current userId = " + userId);
        return userId;
    }

    public Group createGroup(String ownerId, CreateGroupRequest req) {
        String roomId = generateRoomId();

        Group group = new Group();
        group.setRoomId(roomId);
        group.setName(req.getName());
        group.setOwnerId(ownerId);
        group.setDescription(req.getDescription());
        group.setRoomType("group");
        group.setCreatedAt(Instant.now());
        group.setActive(true);
        groupRepository.save(group);

        addMember(roomId, ownerId, "admin");

        req.getMemberIds().forEach(id -> addMember(roomId, id, "member"));

        return group;
    }

    private void addMember(String roomId, String userId, String role) {
        GroupMember member = new GroupMember();
        member.setMembershipId(generateMembershipId());
        member.setUserId(userId);
        member.setRole(role);
        member.setJoinedAt(Instant.now());
        member.setRoomId(roomId);
        member.setActive(true);
        memberRepository.save(member);
    }

    public GroupInvitation inviteMember(String inviterId, InviteMemberRequest req) {

        GroupInvitation invite = new GroupInvitation();
        invite.setInvitationId(generateRoomId());
        invite.setInviterId(inviterId);
        invite.setInviteeId(req.getInviteeId());
        invite.setStatus("accepted");
        invite.setCreatedAt(Instant.now());
        invite.setRoomId(req.getRoomId());
        invite.setActive(true);
        invitationRepository.save(invite);

        addMember(invite.getRoomId(), invite.getInviteeId(), "member");
        return invitationRepository.save(invite);
    }

    // public List<Group> getMyGroups(String userId) {
    //     System.out.println("Fetching groups for userId: " + userId);

    //     List<String> roomIds = memberRepository.findByUserIdAndIsActiveTrue(userId)
    //         .stream().map(GroupMember::getRoomId).collect(Collectors.toList());

    //     return groupRepository.findAllByRoomIdInAndIsActiveTrue(roomIds);
    // }

    public List<Group> getGroups(String userId) {
        List<String> roomIds = memberRepository.findByUserIdAndIsActiveTrue(userId)
            .stream().map(GroupMember::getRoomId).collect(Collectors.toList());

        return groupRepository.findAllByRoomIdInAndIsActiveTrue(roomIds);
    }

    public void deleteGroup(String roomId) {
        Group group = groupRepository.findByRoomIdAndIsActiveTrue(roomId)
            .orElseThrow(() -> new RuntimeException("Group not found"));
        group.setActive(false);
        groupRepository.save(group);

        memberRepository.findByRoomIdAndIsActiveTrue(roomId)
            .forEach(m -> {
                m.setActive(false);
                memberRepository.save(m);
            });
    }

    private String generateRoomId() {
        return "R" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String generateMembershipId() {
        return "M" + UUID.randomUUID().toString().substring(0, 8);
    }
}