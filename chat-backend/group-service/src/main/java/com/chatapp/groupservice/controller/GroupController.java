package com.chatapp.groupservice.controller;

import com.chatapp.groupservice.dto.*;
import com.chatapp.groupservice.model.Group;
import com.chatapp.groupservice.model.GroupInvitation;
import com.chatapp.groupservice.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @PostMapping
    public Group createGroup(@RequestHeader("X-User-Id") String ownerId, @RequestBody CreateGroupRequest req) {
        return groupService.createGroup(ownerId, req);
    }

    @PostMapping("/invite")
    public GroupInvitation invite(@RequestHeader("X-User-Id") String inviterId, @RequestBody InviteMemberRequest req) {
        return groupService.inviteMember(inviterId, req);
    }

    // @GetMapping("/my")
    // public List<Group> getMyGroups(@RequestHeader("X-User-Id") String userId) {
    //     return groupService.getMyGroups(userId);
    // }

    @GetMapping("/{userId}")
    public List<Group> getGroupsbyUserId(@PathVariable String userId) {
        return groupService.getGroups(userId);
    }

    @DeleteMapping("/{roomId}")
    public void deleteGroup(@PathVariable String roomId) {
        groupService.deleteGroup(roomId);
    }
}